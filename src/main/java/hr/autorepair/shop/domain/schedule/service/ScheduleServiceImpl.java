package hr.autorepair.shop.domain.schedule.service;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.joborder.repository.JobOrderRepository;
import hr.autorepair.shop.domain.schedule.dto.FreePeriodResponse;
import hr.autorepair.shop.domain.schedule.dto.ScheduleResponse;
import hr.autorepair.shop.domain.workplace.model.Workplace;
import hr.autorepair.shop.domain.workplace.repository.WorkplaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static hr.autorepair.common.constants.BusinessConstants.*;
import static hr.autorepair.common.utils.DateUtil.isDateWeekend;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final JobOrderRepository jobOrderRepository;
    private final WorkplaceRepository workplaceRepository;

    @Override
    public List<ScheduleResponse> getScheduleForNext30Days(LocalTime repairTime) {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysFromToday = today.plusDays(30);
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();

        for (LocalDate date = today; !date.isAfter(thirtyDaysFromToday); date = date.plusDays(1)) {
            Map<Workplace, List<JobOrder>> jobOrdersByWorkplace = jobOrderRepository.findByOrderDate(date).stream()
                    .collect(Collectors.groupingBy(JobOrder::getWorkplace));
            LocalTime currentSlotStart = OPENING_TIME;
            LocalTime currentAvailableStart = null;
            List<FreePeriodResponse> freePeriodResponses = new ArrayList<>();

            while (currentSlotStart.plus(SLOT_DURATION).isBefore(CLOSING_TIME) || currentSlotStart.plus(SLOT_DURATION).equals(CLOSING_TIME)) {
                boolean isFreeInAtLeastOneWorkplace = false;

                for (Map.Entry<Workplace, List<JobOrder>> entry : jobOrdersByWorkplace.entrySet()) {

                    List<JobOrder> jobOrdersAtWorkplace = entry.getValue();
                    boolean isFreeInCurrentWorkplace = true;

                    for(JobOrder jobOrder : jobOrdersAtWorkplace){
                        if(currentSlotStart.isBefore(jobOrder.getTimeTo()) && currentSlotStart.plus(SLOT_DURATION).isAfter(jobOrder.getTimeFrom())){
                            isFreeInCurrentWorkplace = false;
                            break;
                        }
                    }

                    if(isFreeInCurrentWorkplace){
                        isFreeInAtLeastOneWorkplace = true;
                        break;
                    }
                }

                if(isFreeInAtLeastOneWorkplace){
                    if(currentAvailableStart == null)
                        currentAvailableStart = currentSlotStart;
                }
                else {
                    if(currentAvailableStart != null){
                        freePeriodResponses.add((new FreePeriodResponse(currentAvailableStart, currentSlotStart)));
                        currentAvailableStart = null;
                    }
                }

                currentSlotStart = currentSlotStart.plus(SLOT_DURATION);
            }

            if(currentAvailableStart != null )
                freePeriodResponses.add((new FreePeriodResponse(currentAvailableStart, currentSlotStart)));

            if (!freePeriodResponses.isEmpty()) {
                ScheduleResponse scheduleResponse = new ScheduleResponse();
                scheduleResponse.setFreeDate(date);
                scheduleResponse.setFreePeriods(freePeriodResponses);
                scheduleResponses.add(scheduleResponse);
            }
            else{
                ScheduleResponse scheduleResponse = new ScheduleResponse();
                scheduleResponse.setFreeDate(date);
                scheduleResponse.setFreePeriods(List.of(new FreePeriodResponse(OPENING_TIME, CLOSING_TIME)));
                scheduleResponses.add(scheduleResponse);
            }
        }

        List<ScheduleResponse> finalScheduleResponseList;
        if(repairTime != null){
            scheduleResponses.forEach(scheduleResponse -> scheduleResponse.setFreePeriods(
                    scheduleResponse.getFreePeriods().stream()
                            .filter(freePeriod -> {
                                Duration duration = Duration.between(freePeriod.getFreeFrom(), freePeriod.getFreeTo());
                                LocalTime difference = LocalTime.MIDNIGHT.plusHours(duration.toHours()).plusMinutes(duration.toMinutes() % 60);
                                return !repairTime.isAfter(difference);
                            })
                            .toList()
            ));

            finalScheduleResponseList =  scheduleResponses.stream()
                    .filter(scheduleResponse -> !scheduleResponse.getFreePeriods().isEmpty())
                    .toList();
        }
        else
            finalScheduleResponseList =  scheduleResponses;

        return finalScheduleResponseList.stream()
                .filter(scheduleResponse -> !isDateWeekend(scheduleResponse.getFreeDate()))
                .toList();
    }

    @Override
    public Optional<Workplace> getFreeWorkplaceForDateAndPeriod(LocalDate date, LocalTime from, LocalTime to) {
        // Fetch all non-deleted workplaces
        List<Workplace> allWorkplaces = workplaceRepository.findByIsDeletedFalse();

        // Fetch job orders for the given date
        List<JobOrder> jobOrders = jobOrderRepository.findByOrderDate(date);

        // Check each workplace for availability
        for (Workplace workplace : allWorkplaces) {
            // Fetch job orders for the current workplace on the specified date
            List<JobOrder> jobOrdersAtWorkplace = jobOrders.stream()
                    .filter(jobOrder -> jobOrder.getWorkplace().equals(workplace))
                    .toList();

            // Check if the given period overlaps with any job orders at the current workplace
            boolean isFree = jobOrdersAtWorkplace.stream().noneMatch(jobOrder ->
                    from.isBefore(jobOrder.getTimeTo()) && to.isAfter(jobOrder.getTimeFrom())
            );

            // If the workplace is free, return it
            if (isFree) {
                return Optional.of(workplace);
            }
        }

        // If no available workplace found, return an empty Optional
        return Optional.empty();
    }

}

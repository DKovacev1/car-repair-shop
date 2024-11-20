package hr.autorepair.shop.domain.joborder.util;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.car.model.Car;
import hr.autorepair.shop.domain.car.repository.CarRepository;
import hr.autorepair.shop.domain.joborder.dto.AddJobOrderRequest;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.joborderpart.model.JobOrderPart;
import hr.autorepair.shop.domain.joborderstatus.model.JobOrderStatus;
import hr.autorepair.shop.domain.joborderstatus.repository.JobOrderStatusRepository;
import hr.autorepair.shop.domain.joborderstatus.util.JobOrderStatusEnum;
import hr.autorepair.shop.domain.part.model.Part;
import hr.autorepair.shop.domain.part.repository.PartRepository;
import hr.autorepair.shop.domain.repair.model.Repair;
import hr.autorepair.shop.domain.repair.repository.RepairRepository;
import hr.autorepair.shop.domain.schedule.service.ScheduleService;
import hr.autorepair.shop.domain.workplace.model.Workplace;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static hr.autorepair.common.constants.BusinessConstants.CLOSING_TIME;
import static hr.autorepair.common.constants.BusinessConstants.OPENING_TIME;
import static hr.autorepair.common.constants.MessageConstants.*;
import static hr.autorepair.common.constants.MessageConstants.JOB_ORDER_STATUS_NOT_EXISTS;
import static hr.autorepair.common.utils.DateUtil.isDateWeekend;

@Component
@AllArgsConstructor
public class AddJobOrderValidation {

    private final CarRepository carRepository;
    private final RepairRepository repairRepository;
    private final JobOrderStatusRepository jobOrderStatusRepository;
    private final ScheduleService scheduleService;
    private final PartRepository partRepository;

    public void validateAndFillJobOrder(JobOrder jobOrder, AddJobOrderRequest request) {
        AppUser jobOrderAppUserEmployee = UserDataUtils.getUserPrincipal().getAppUser();
        Car car = carRepository.findByIdCarAndIsDeletedFalse(request.getIdCar())
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(CAR_NOT_EXISTS, request.getIdCar())));

        Set<Repair> repairs = new HashSet<>();
        request.getRepairIds().forEach(repairId -> {
            Repair repair = repairRepository.findByIdRepairAndIsDeletedFalse(repairId)
                    .orElseThrow(() -> new BadRequestException(MessageFormat.format(REPAIR_NOT_EXISTS, repairId)));
            repairs.add(repair);
        });

        request.getParts().forEach(partDto -> {
            Part part = partRepository.findByIdPartAndIsDeletedFalse(partDto.getIdPart())
                    .orElseThrow(() -> new BadRequestException(MessageFormat.format(PART_NOT_EXIST, partDto.getIdPart())));

            JobOrderPart jobOrderPart = new JobOrderPart();
            jobOrderPart.setPart(part);
            jobOrderPart.setQuantity(partDto.getQuantity());
            jobOrder.addJobOrderPart(jobOrderPart);
        });

        if(isDateWeekend(request.getOrderDate()))
            throw new BadRequestException("Job order can not be on weekend!");

        validateTime("Starting", request.getTimeFrom());
        validateTime("Ending", request.getTimeTo());

        if(request.getTimeFrom().isAfter(request.getTimeTo()))
            throw new BadRequestException("Start time can not be after end time!");

        Duration jobDuration = Duration.between(request.getTimeFrom(),request.getTimeTo());
        LocalTime jobDurationTime = LocalTime.of(jobDuration.toHoursPart(), jobDuration.toMinutesPart() % 60);

        Duration minimumRepairDuration= repairs.stream()
                .map(Repair::getRepairTime)
                .map(time -> Duration.between(LocalTime.MIN, time))
                .reduce(Duration.ZERO, Duration::plus);
        LocalTime minimumRepairDurationTime = LocalTime.of(minimumRepairDuration.toHoursPart(), minimumRepairDuration.toMinutesPart() % 60);

        if(jobDurationTime.isBefore(minimumRepairDurationTime)){
            String repairStr = repairs.size() > 1 ? "repairs" : "repair";
            throw new BadRequestException("Minimum required time for given " + repairStr + " is "
                    + minimumRepairDurationTime.getHour() + " hours and "
                    + minimumRepairDurationTime.getMinute() + " minutes!");
        }

        Workplace workplace = scheduleService.getFreeWorkplaceForDateAndPeriod(request.getOrderDate(), request.getTimeFrom(), request.getTimeTo())
                .orElseThrow(() -> new BadRequestException(SCHEDULE_NOT_FREE));

        String status = JobOrderStatusEnum.CREATED.getName();
        JobOrderStatus jobOrderStatus = jobOrderStatusRepository.findByName(status)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(JOB_ORDER_STATUS_NOT_EXISTS, status)));

        jobOrder.setDescription(request.getDescription());
        jobOrder.setOrderDate(request.getOrderDate());
        jobOrder.setTimeFrom(getOnlyHoursAndMinutes(request.getTimeFrom()));
        jobOrder.setTimeTo(getOnlyHoursAndMinutes(request.getTimeTo()));
        jobOrder.setIsDeleted(false);
        jobOrder.setWorkplace(workplace);
        jobOrder.setJobOrderAppUserEmployee(jobOrderAppUserEmployee);
        jobOrder.setCar(car);
        jobOrder.setJobOrderStatus(jobOrderStatus);
        jobOrder.setRepairs(repairs);
    }

    private void validateTime(String timeName, LocalTime time){
        if(time.getMinute() % 30 != 0)
            throw new BadRequestException(timeName + " minutes can be only 0 or 30 minutes!");

        if(time.getHour() < OPENING_TIME.getHour())
            throw new BadRequestException(timeName + " can not be before " + OPENING_TIME + ".");

        if(time.getHour() > CLOSING_TIME.getHour())
            throw new BadRequestException(timeName + " can not be after " + CLOSING_TIME + ".");
    }

    private LocalTime getOnlyHoursAndMinutes(LocalTime time){
        return LocalTime.of(time.getHour(), time.getMinute());
    }

}

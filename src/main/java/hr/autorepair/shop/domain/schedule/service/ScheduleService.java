package hr.autorepair.shop.domain.schedule.service;

import hr.autorepair.shop.domain.schedule.dto.ScheduleResponse;
import hr.autorepair.shop.domain.workplace.model.Workplace;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<ScheduleResponse> getScheduleForNext30Days();
    Optional<Workplace> getFreeWorkplaceForDateAndPeriod(LocalDate date, LocalTime from, LocalTime to);
}

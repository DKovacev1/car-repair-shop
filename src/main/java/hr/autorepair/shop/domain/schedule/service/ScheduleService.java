package hr.autorepair.shop.domain.schedule.service;

import hr.autorepair.shop.domain.schedule.dto.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponse> getScheduleForNext30Days();
}

package hr.autorepair.shop.domain.schedule.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ScheduleResponse {
    private LocalDate freeDate;
    private List<FreePeriodResponse> freePeriods;
}

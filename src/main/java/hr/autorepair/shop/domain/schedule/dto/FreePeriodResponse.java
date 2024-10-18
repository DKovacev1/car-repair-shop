package hr.autorepair.shop.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class FreePeriodResponse {
    private LocalTime freeFrom;
    private LocalTime freeTo;
}

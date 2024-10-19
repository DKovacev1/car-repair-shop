package hr.autorepair.shop.domain.joborder.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class AddJobOrderRequest {
    @NotEmpty(message = "Description {error.required}")
    private String description;
    @NotNull(message = "Order date {error.required}")
    private LocalDate orderDate;
    @NotNull(message = "Starting time {error.required}")
    private LocalTime timeFrom;
    @NotNull(message = "Ending time {error.required}")
    private LocalTime timeTo;
    @NotNull(message = "Car {error.required}")
    private Long idCar;
    @NotEmpty(message = "At least one repair {error.required}")
    private Set<Long> repairIds;
}

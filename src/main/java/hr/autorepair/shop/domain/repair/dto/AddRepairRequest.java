package hr.autorepair.shop.domain.repair.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class AddRepairRequest {
    @NotEmpty(message = "Name {error.required}")
    private String name;
    @NotNull(message = "Cost {error.required}")
    private BigDecimal cost;
    @NotNull(message = "Repair time {error.required}")
    private LocalTime repairTime;
}

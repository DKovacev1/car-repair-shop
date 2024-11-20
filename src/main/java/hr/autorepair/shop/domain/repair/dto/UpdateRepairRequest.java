package hr.autorepair.shop.domain.repair.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Objects;

@Data
public class UpdateRepairRequest {
    @NotEmpty(message = "Name {error.required}")
    private String name;
    @NotNull(message = "Cost {error.required}")
    private BigDecimal cost;
    @NotNull(message = "Repair time {error.required}")
    private LocalTime repairTime;

    public boolean hasChanges(UpdateRepairRequest other) {
        return !(Objects.equals(name, other.name)
                && Objects.equals(cost, other.cost)
                && Objects.equals(repairTime, other.repairTime));
    }
}

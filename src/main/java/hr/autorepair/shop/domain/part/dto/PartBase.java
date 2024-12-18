package hr.autorepair.shop.domain.part.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartBase {
    @NotEmpty(message = "Name {error.required}")
    private String name;
    @NotNull(message = "Cost {error.required}")
    private BigDecimal cost;
}

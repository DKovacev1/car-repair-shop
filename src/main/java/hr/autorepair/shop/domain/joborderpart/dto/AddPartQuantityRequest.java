package hr.autorepair.shop.domain.joborderpart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddPartQuantityRequest {
    @NotNull(message = "Part id {error.required}")
    private Long idPart;
    @NotNull(message = "Quantity {error.required}")
    @Min(value = 1, message = "Minimum quantity is 1!")
    private Integer quantity;
}

package hr.autorepair.shop.domain.receipt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddReceiptRequest {
    private BigDecimal additionalDiscount;
    @NotNull(message = "Payment {error.required}")
    private Long idPayment;
    @NotNull(message = "Job order {error.required}")
    private Long idJobOrder;
}

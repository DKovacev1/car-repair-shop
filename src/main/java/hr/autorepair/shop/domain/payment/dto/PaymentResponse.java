package hr.autorepair.shop.domain.payment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponse {
    private Long idPayment;
    private String name;
    private BigDecimal discount;
}

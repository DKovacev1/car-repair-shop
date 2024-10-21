package hr.autorepair.shop.domain.receipt.dto;

import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.payment.dto.PaymentResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReceiptResponse {
    private Long idReceipt;
    private LocalDateTime createdAt;
    private BigDecimal loyaltyDiscount;
    private BigDecimal additionalDiscount;
    private BigDecimal repairCostSum;
    private BigDecimal totalCost;
    private Boolean isDeleted;
    private PaymentResponse payment;
    private JobOrderResponse jobOrder;
    private AppUserResponse receiptAppUserEmployee;
}

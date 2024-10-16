package hr.autorepair.shop.domain.receipt.dto;

import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReceiptResponse {
    private Long idReceipt;
    private LocalDateTime createdAt;
    private BigDecimal totalCost;
    private JobOrderResponse jobOrder;
    private AppUserResponse receiptAppUserEmployee;
}

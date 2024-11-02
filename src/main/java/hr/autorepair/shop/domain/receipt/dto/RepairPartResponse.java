package hr.autorepair.shop.domain.receipt.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RepairPartResponse {
    private String name;
    private BigDecimal cost;
    private String quantity;
    private BigDecimal total;
}

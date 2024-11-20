package hr.autorepair.shop.domain.repair.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class RepairResponse {
    private Long idRepair;
    private String name;
    private BigDecimal cost;
    private LocalTime repairTime;
}

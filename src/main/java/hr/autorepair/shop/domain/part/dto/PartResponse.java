package hr.autorepair.shop.domain.part.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartResponse {
    private Long idPart;
    private String name;
    private BigDecimal cost;
}

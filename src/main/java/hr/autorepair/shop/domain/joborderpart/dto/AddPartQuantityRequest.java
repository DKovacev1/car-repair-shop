package hr.autorepair.shop.domain.joborderpart.dto;

import lombok.Data;

@Data
public class AddPartQuantityRequest {
    private Long idPart;
    private Integer quantity;
}

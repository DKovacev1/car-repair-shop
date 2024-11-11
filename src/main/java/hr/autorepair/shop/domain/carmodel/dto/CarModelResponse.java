package hr.autorepair.shop.domain.carmodel.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarModelResponse {
    private BigDecimal idCarModel;
    private String name;
}

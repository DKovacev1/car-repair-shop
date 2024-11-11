package hr.autorepair.shop.domain.carmaker.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarMakerResponse {
    private BigDecimal idCarMaker;
    private String name;
}


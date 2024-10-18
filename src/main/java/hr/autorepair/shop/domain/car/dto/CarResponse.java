package hr.autorepair.shop.domain.car.dto;

import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarResponse {
    private Long idCar;
    private String registrationPlate;
    private String maker;
    private String model;
    private Long cylinders;
    private BigDecimal displacement;
    private Long yearOfProduction;
    private String fuelType;
    private AppUserResponse carOwner;
}

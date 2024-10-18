package hr.autorepair.shop.domain.car.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddCarRequest {
    @NotEmpty(message = "Registration plate {error.required}")
    private String registrationPlate;
    @NotEmpty(message = "Maker {error.required}")
    private String maker;
    @NotEmpty(message = "Model {error.required}")
    private String model;
    @NotNull(message = "Number of cylinders {error.required}")
    private Long cylinders;
    @NotNull(message = "Displacement {error.required}")
    private BigDecimal displacement;
    @NotNull(message = "Year of production {error.required}")
    private Long yearOfProduction;
    @NotEmpty(message = "Fuel type {error.required}")
    private String fuelType;
    private Long idCarOwner;//idAppUser (optional)
}

package hr.autorepair.shop.domain.car.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class UpdateCarRequest {
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

    public boolean hasChanges(UpdateCarRequest other) {
        return !(Objects.equals(registrationPlate, other.registrationPlate)
                && Objects.equals(maker, other.maker)
                && Objects.equals(model, other.model)
                && Objects.equals(cylinders, other.cylinders)
                && Objects.equals(displacement, other.displacement)
                && Objects.equals(yearOfProduction, other.yearOfProduction)
                && Objects.equals(fuelType, other.fuelType));
    }
}

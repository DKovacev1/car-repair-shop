package hr.autorepair.shop.domain.car.model;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long idCar;
    private String maker;
    private String model;
    private Long cylinders;
    private BigDecimal displacement;
    private Long yearOfProduction;
    private String fuelType;
    @ManyToOne
    private AppUser carOwner;

    @OneToMany(mappedBy = "car")
    private Set<JobOrder> jobOrders;//JobOrder upravlja ovime*/
}

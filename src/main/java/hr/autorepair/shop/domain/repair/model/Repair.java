package hr.autorepair.shop.domain.repair.model;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

/**
 * Entitet koji predstavlja popravke na autu
 */
@Getter
@Setter
@Entity
public class Repair {
    @Id
    @GeneratedValue
    private Long idRepair;
    private String name;
    private BigDecimal cost;
    private LocalTime repairTime;

    @ManyToMany(mappedBy = "repairs")
    private Set<JobOrder> jobOrders;//JobOrder upravlja ovime
}

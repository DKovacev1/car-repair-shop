package hr.autorepair.shop.domain.repair.model;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Entitet koji predstavlja popravke na autu
 */
@Data
@Entity
public class Repair {
    @Id
    @GeneratedValue
    private Long idRepair;
    private String name;
    private BigDecimal cost;
    private Long repairTime;

    @ManyToMany(mappedBy = "repairs")
    private Set<JobOrder> jobOrders;//JobOrder upravlja ovime
}

package hr.autorepair.shop.domain.workplace.model;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

/**
 * Entitet koji predstavlja radionice/odjele
 */
@Data
@Entity
public class Workplace {
    @Id
    @GeneratedValue
    private Long idWorkplace;
    private String name;

    @OneToMany(mappedBy = "workplace")
    private Set<JobOrder> jobOrders;//JobOrder upravlja ovime
}

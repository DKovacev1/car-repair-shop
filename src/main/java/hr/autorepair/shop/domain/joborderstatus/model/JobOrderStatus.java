package hr.autorepair.shop.domain.joborderstatus.model;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class JobOrderStatus {
    @Id
    @GeneratedValue
    private Long idJobOrderStatus;
    private String name;

    @OneToMany(mappedBy = "jobOrderStatus")
    private List<JobOrder> jobOrders;//JobOrder controls this
}

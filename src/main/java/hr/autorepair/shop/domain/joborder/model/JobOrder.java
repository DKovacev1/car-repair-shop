package hr.autorepair.shop.domain.joborder.model;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.car.model.Car;
import hr.autorepair.shop.domain.joborderpart.model.JobOrderPart;
import hr.autorepair.shop.domain.joborderstatus.model.JobOrderStatus;
import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.repair.model.Repair;
import hr.autorepair.shop.domain.workplace.model.Workplace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class JobOrder {
    @Id
    @GeneratedValue
    private Long idJobOrder;
    private String description;
    private LocalDate orderDate;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private Boolean isDeleted;
    @ManyToOne
    private Workplace workplace;
    @ManyToOne
    private AppUser jobOrderAppUserEmployee;//which employee created the job order
    @ManyToMany
    private Set<Repair> repairs;
    @ManyToOne
    private Car car;
    @ManyToOne
    private JobOrderStatus jobOrderStatus;

    @ManyToMany(mappedBy = "jobOrders")
    private Set<Receipt> receipts;//Receipt controls this
    @OneToMany(mappedBy = "jobOrder", cascade = CascadeType.ALL)
    private Set<JobOrderPart> parts = new HashSet<>();

    public void addJobOrderPart(JobOrderPart jobOrderPart){
        parts.add(jobOrderPart);
        jobOrderPart.setJobOrder(this);
    }

}

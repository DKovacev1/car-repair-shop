package hr.autorepair.shop.domain.joborder.model;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.car.model.Car;
import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.repair.model.Repair;
import hr.autorepair.shop.domain.workplace.model.Workplace;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Entitet koji predstavlja naloge
 */
@Data
@Entity
public class JobOrder {
    @Id
    @GeneratedValue
    private Long idJobOrder;
    private String description;
    private LocalDate orderDate;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private Boolean isFinished;
    @ManyToOne
    private Workplace workplace;
    @ManyToOne
    private AppUser jobOrderAppUserClient;//za kojeg klijenta je napravljen nalog
    @ManyToOne
    private AppUser jobOrderAppUserEmployee;//koji zaposlenik je napravio nalog
    @ManyToMany
    private Set<Repair> repairs;
    @ManyToOne
    private Car car;

    @OneToOne(mappedBy = "jobOrder")
    private Receipt receipt;//Receipt upravlja ovime
}
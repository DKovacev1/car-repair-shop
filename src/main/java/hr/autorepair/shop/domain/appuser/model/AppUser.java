package hr.autorepair.shop.domain.appuser.model;

import hr.autorepair.shop.domain.car.model.Car;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.role.model.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Entitet koji predstavlja korisnika aplikacije
 */
@Data
@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private Long idAppUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp tstamp;
    private Boolean isActivated;
    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "jobOrderAppUserEmployee")
    private Set<JobOrder> jobOrdersEmployee;//JobOrder upravlja ovime
    @OneToMany(mappedBy = "receiptAppUserEmployee")
    private Set<Receipt> receiptsEmployee;//Receipt upravlja ovime
    @OneToMany(mappedBy = "carOwner")
    private Set<Car> cars;//Car upravlja ovime
}

package hr.autorepair.shop.domain.appuser.model;

import hr.autorepair.shop.domain.car.model.Car;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.receipt.model.Receipt;
import hr.autorepair.shop.domain.role.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private Long idAppUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Boolean isActivated;//can user sign in to application
    private Boolean isDeleted;//is profile deleted or active
    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "jobOrderAppUserEmployee")
    private Set<JobOrder> jobOrdersEmployee;//JobOrder controlls this
    @OneToMany(mappedBy = "receiptAppUserEmployee")
    private Set<Receipt> receiptsEmployee;//Receipt controlls this
    @OneToMany(mappedBy = "carOwner")
    private Set<Car> cars;//Car controlls this
}

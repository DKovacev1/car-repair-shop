package hr.autorepair.shop.appuser.model;

import hr.autorepair.shop.role.model.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

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
}

package hr.autorepair.shop.appuser.model;

import hr.autorepair.shop.role.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private Role role;
}

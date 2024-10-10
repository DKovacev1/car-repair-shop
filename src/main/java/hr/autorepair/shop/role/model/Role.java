package hr.autorepair.shop.role.model;

import hr.autorepair.shop.appuser.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long idRole;
    private String name;
    @OneToMany
    private List<AppUser> appUsers;
}

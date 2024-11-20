package hr.autorepair.shop.domain.role.model;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long idRole;
    private String name;
    
    @OneToMany(mappedBy = "role") //AppUser controlls this
    private Set<AppUser> appUsers;
}

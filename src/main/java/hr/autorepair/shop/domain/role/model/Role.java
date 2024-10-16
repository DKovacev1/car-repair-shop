package hr.autorepair.shop.domain.role.model;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

/**
 * Entitet koji predstavlja role/uloge korisnika
 */
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long idRole;
    private String name;
    
    @OneToMany(mappedBy = "role") //AppUser upravlja ovime
    private Set<AppUser> appUsers;
}

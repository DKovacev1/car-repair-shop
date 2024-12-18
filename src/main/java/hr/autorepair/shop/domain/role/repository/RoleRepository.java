package hr.autorepair.shop.domain.role.repository;

import hr.autorepair.shop.domain.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    List<Role> findAll();
    List<Role> findByNameNot(String name);
}

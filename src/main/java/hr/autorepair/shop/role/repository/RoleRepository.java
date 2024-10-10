package hr.autorepair.shop.role.repository;

import hr.autorepair.shop.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

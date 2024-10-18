package hr.autorepair.shop.domain.appuser.repository;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {
    Optional<AppUser> findByEmailAndIsDeletedFalseAndIsActivatedTrue(String email);
    Optional<AppUser> findByIdAppUserAndIsDeletedFalse(Long idAppUser);
}

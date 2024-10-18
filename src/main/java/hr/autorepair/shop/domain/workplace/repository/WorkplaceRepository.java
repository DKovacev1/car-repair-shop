package hr.autorepair.shop.domain.workplace.repository;

import hr.autorepair.shop.domain.workplace.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
    List<Workplace> findByIsDeletedFalse();
    Optional<Workplace> findByIdWorkplaceAndIsDeletedFalse(Long idWorkplace);
}

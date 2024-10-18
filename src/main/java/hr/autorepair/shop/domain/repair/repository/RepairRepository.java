package hr.autorepair.shop.domain.repair.repository;

import hr.autorepair.shop.domain.repair.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    List<Repair> findByIsDeletedFalse();
    Optional<Repair> findByIdRepairAndIsDeletedFalse(Long idRepair);
}

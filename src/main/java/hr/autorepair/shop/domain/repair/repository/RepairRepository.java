package hr.autorepair.shop.domain.repair.repository;

import hr.autorepair.shop.domain.repair.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    Repair save(Repair repair);
}

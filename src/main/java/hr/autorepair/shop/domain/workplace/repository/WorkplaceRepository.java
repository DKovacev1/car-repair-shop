package hr.autorepair.shop.domain.workplace.repository;

import hr.autorepair.shop.domain.workplace.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
    Workplace save(Workplace workplace);
}

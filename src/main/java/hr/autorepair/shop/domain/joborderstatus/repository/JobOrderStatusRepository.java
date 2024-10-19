package hr.autorepair.shop.domain.joborderstatus.repository;

import hr.autorepair.shop.domain.joborderstatus.model.JobOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobOrderStatusRepository extends JpaRepository<JobOrderStatus, Long> {
    Optional<JobOrderStatus> findByName(String name);
}

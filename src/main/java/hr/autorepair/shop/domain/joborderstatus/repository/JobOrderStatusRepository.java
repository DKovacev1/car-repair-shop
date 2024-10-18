package hr.autorepair.shop.domain.joborderstatus.repository;

import hr.autorepair.shop.domain.joborderstatus.model.JobOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOrderStatusRepository extends JpaRepository<JobOrderStatus, Long> {
}

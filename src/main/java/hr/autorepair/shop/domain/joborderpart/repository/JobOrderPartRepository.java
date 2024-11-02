package hr.autorepair.shop.domain.joborderpart.repository;

import hr.autorepair.shop.domain.joborderpart.model.JobOrderPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOrderPartRepository extends JpaRepository<JobOrderPart, Long> {
}

package hr.autorepair.shop.domain.joborder.repository;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOrderRepository extends JpaRepository<JobOrder, Long> {
    JobOrder save(JobOrder jobOrder);
}

package hr.autorepair.shop.domain.joborder.repository;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobOrderRepository extends JpaRepository<JobOrder, Long> {
    @Query("select j from JobOrder j where j.car.carOwner.idAppUser = ?1 and j.car.carOwner.isDeleted = false")
    List<JobOrder> findByIdAppUser(Long idAppUser);
}

package hr.autorepair.shop.domain.joborder.repository;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.workplace.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JobOrderRepository extends JpaRepository<JobOrder, Long> {
    @Query("""
            select j from JobOrder j
            where j.car.carOwner.idAppUser = ?1 and j.isDeleted = false""")
    List<JobOrder> findByIdAppUser(Long idAppUser);
    @Query("""
            select count(j) from JobOrder j
            where j.car.carOwner.idAppUser = ?1 and j.isDeleted = false and j.orderDate > ?2""")
    long findJobOrdersForUserAfterDate(Long idAppUser, LocalDate date);
    @Query("""
            select j from JobOrder j
            where j.isDeleted = false""")
    List<JobOrder> findAll();
    @Query("""
            select j from JobOrder j
            where j.idJobOrder = ?1 and j.isDeleted = false""")
    Optional<JobOrder> findByIdJobOrder(Long idJobOrder);
    @Query("select j from JobOrder j where j.orderDate = ?1 and j.isDeleted = false")
    List<JobOrder> findByOrderDate(LocalDate orderDate);
    List<JobOrder> findByWorkplace_IdWorkplaceAndOrderDate(Long idWorkplace, LocalDate orderDate);
    List<JobOrder> findByOrderDateAndWorkplace(LocalDate date, Workplace workplace);
    @Query("""
            select (count(j) > 0) from JobOrder j inner join j.receipts receipts
            where j.receipts is not empty and receipts.isDeleted = false and j.idJobOrder = ?1""")
    boolean exitsActiveReceiptForJobOrder(Long idJobOrder);
}

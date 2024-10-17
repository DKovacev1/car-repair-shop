package hr.autorepair.shop.domain.receipt.repository;

import hr.autorepair.shop.domain.receipt.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @Query("""
            select r from Receipt r
            where r.jobOrder.car.carOwner.idAppUser = ?1 and r.jobOrder.car.carOwner.isDeleted = false""")
    List<Receipt> findByIdAppUser(Long idAppUser);

}

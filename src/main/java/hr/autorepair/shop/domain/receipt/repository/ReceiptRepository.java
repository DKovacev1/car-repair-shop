package hr.autorepair.shop.domain.receipt.repository;

import hr.autorepair.shop.domain.receipt.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByIsDeletedFalse();
    @Query("""
            select r from Receipt r inner join r.jobOrders jobOrders
            where jobOrders.car.carOwner.idAppUser = ?1 and r.isDeleted = false""")
    List<Receipt> findByIdAppUser(Long idAppUser);
    Optional<Receipt> findByIdReceiptAndIsDeletedFalse(Long idReceipt);
}

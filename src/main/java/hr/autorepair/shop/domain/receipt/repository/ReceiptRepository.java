package hr.autorepair.shop.domain.receipt.repository;

import hr.autorepair.shop.domain.receipt.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}

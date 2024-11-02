package hr.autorepair.shop.domain.payment.repository;

import hr.autorepair.shop.domain.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

package hr.autorepair.shop.domain.payment.model;

import hr.autorepair.shop.domain.receipt.model.Receipt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long idPayment;
    private String name;
    private BigDecimal discount;

    @OneToMany(mappedBy = "payment")
    private Set<Receipt> receipts;//Receipt controls this
}

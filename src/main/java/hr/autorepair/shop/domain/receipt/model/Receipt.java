package hr.autorepair.shop.domain.receipt.model;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.payment.model.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Receipt {
    @Id
    @GeneratedValue
    private Long idReceipt;
    private LocalDateTime createdAt;
    private BigDecimal loyaltyDiscount;
    private BigDecimal additionalDiscount;
    private BigDecimal repairCostSum;
    private BigDecimal partsCostSum;
    private BigDecimal totalCost;
    private Boolean isDeleted;
    @ManyToOne
    private Payment payment;
    @ManyToMany
    private Set<JobOrder> jobOrders;
    @ManyToOne
    private AppUser receiptAppUserEmployee;
}

package hr.autorepair.shop.domain.receipt.model;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.joborder.model.JobOrder;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entitet koji predstavlja račun za odrađeni nalog
 */
@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue
    private Long idReceipt;
    private LocalDateTime createdAt;
    private BigDecimal totalCost;
    @OneToOne
    private JobOrder jobOrder;
    @ManyToOne
    private AppUser receiptAppUserEmployee;
}

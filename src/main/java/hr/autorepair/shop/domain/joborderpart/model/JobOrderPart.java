package hr.autorepair.shop.domain.joborderpart.model;

import hr.autorepair.shop.domain.joborder.model.JobOrder;
import hr.autorepair.shop.domain.part.model.Part;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class JobOrderPart {
    @Id
    @GeneratedValue
    private Long idJobOrderPart;
    @ManyToOne
    private JobOrder jobOrder;
    @ManyToOne
    private Part part;
    private Integer quantity;
}

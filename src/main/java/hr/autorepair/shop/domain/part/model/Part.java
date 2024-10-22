package hr.autorepair.shop.domain.part.model;

import hr.autorepair.shop.domain.joborderpart.model.JobOrderPart;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
public class Part {
    @Id
    @GeneratedValue
    private Long idPart;
    private String name;
    private BigDecimal cost;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "part")
    private Set<JobOrderPart> jobOrderParts;//JobOrderPart controls this
}

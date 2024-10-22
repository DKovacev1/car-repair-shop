package hr.autorepair.shop.domain.part.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
}

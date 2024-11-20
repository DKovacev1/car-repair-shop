package hr.autorepair.shop.domain.joborderpart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.part.dto.PartResponse;
import lombok.Data;

@Data
public class JobOrderPartResponse {
    @JsonIgnore
    private Long idJobOrderPart;
    @JsonIgnore
    private JobOrderResponse jobOrder;
    private PartResponse part;
    private Integer quantity;
}

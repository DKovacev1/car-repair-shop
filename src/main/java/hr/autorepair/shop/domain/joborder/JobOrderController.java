package hr.autorepair.shop.domain.joborder;

import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.joborder.service.JobOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job-order")
@AllArgsConstructor
public class JobOrderController {

    private final JobOrderService jobOrderService;

    @GetMapping
    public ResponseEntity<List<JobOrderResponse>> getJobOrders() {
        return ResponseEntity.ok(jobOrderService.getAllJobOrders());
    }

}

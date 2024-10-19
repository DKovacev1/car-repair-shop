package hr.autorepair.shop.domain.joborder;

import hr.autorepair.shop.domain.joborder.dto.AddJobOrderRequest;
import hr.autorepair.shop.domain.joborder.dto.JobOrderResponse;
import hr.autorepair.shop.domain.joborder.service.JobOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{idJobOrder}")
    public ResponseEntity<JobOrderResponse> getJobOrder(@PathVariable Long idJobOrder) {
        return ResponseEntity.ok(jobOrderService.getJobOrder(idJobOrder));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<JobOrderResponse> addJobOrder(@RequestBody AddJobOrderRequest request) {
        return ResponseEntity.ok(jobOrderService.addJobOrder(request));
    }

    @PostMapping("/{idJobOrder}/increment-status")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<JobOrderResponse> addJobOrder(@PathVariable Long idJobOrder) {
        return ResponseEntity.ok(jobOrderService.incrementStatus(idJobOrder));
    }

    @DeleteMapping("/{idJobOrder}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Void> deactivateJobOrder(@PathVariable Long idJobOrder) {
        jobOrderService.deactivateJobOrder(idJobOrder);
        return ResponseEntity.noContent().build();
    }

}

package hr.autorepair.shop.domain.joborderstatus;

import hr.autorepair.shop.domain.joborderstatus.dto.JobOrderStatusResponse;
import hr.autorepair.shop.domain.joborderstatus.service.JobOrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job-order-status")
@AllArgsConstructor
public class JobOrderStatusController {

    private final JobOrderStatusService jobOrderStatusService;

    @GetMapping
    public ResponseEntity<List<JobOrderStatusResponse>> getJobOrderStatuses(){
        return ResponseEntity.ok(jobOrderStatusService.getJobOrderStatuses());
    }

}

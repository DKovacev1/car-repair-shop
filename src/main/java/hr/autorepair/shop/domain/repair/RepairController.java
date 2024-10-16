package hr.autorepair.shop.domain.repair;

import hr.autorepair.shop.domain.repair.dto.RepairResponse;
import hr.autorepair.shop.domain.repair.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repair")
@AllArgsConstructor
public class RepairController {

    private final RepairService repairService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<RepairResponse>> getRepairs(){
        return ResponseEntity.ok(repairService.getRepairs());
    }

}

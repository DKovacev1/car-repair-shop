package hr.autorepair.shop.domain.repair;

import hr.autorepair.shop.domain.repair.dto.AddRepairRequest;
import hr.autorepair.shop.domain.repair.dto.RepairResponse;
import hr.autorepair.shop.domain.repair.dto.UpdateRepairRequest;
import hr.autorepair.shop.domain.repair.service.RepairService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repair")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
@AllArgsConstructor
public class RepairController {

    private final RepairService repairService;

    @GetMapping
    public ResponseEntity<List<RepairResponse>> getRepairs(){
        return ResponseEntity.ok(repairService.getRepairs());
    }

    @GetMapping("/{idRepair}")
    public ResponseEntity<RepairResponse> getRepair(@PathVariable Long idRepair){
        return ResponseEntity.ok(repairService.getRepair(idRepair));
    }

    @PostMapping
    public ResponseEntity<RepairResponse> addRepair(@RequestBody @Valid AddRepairRequest request){
        return ResponseEntity.ok(repairService.addRepair(request));
    }

    @PutMapping("/{idRepair}")
    public ResponseEntity<RepairResponse> updateRepair(
            @PathVariable Long idRepair,
            @RequestBody @Valid UpdateRepairRequest request){
        return ResponseEntity.ok(repairService.updateRepair(idRepair, request));
    }

    @DeleteMapping("/{idRepair}")
    public ResponseEntity<RepairResponse> deactivateRepair(@PathVariable Long idRepair){
        repairService.deactivateRepair(idRepair);
        return ResponseEntity.noContent().build();
    }

}

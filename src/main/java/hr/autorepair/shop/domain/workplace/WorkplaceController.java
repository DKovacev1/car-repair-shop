package hr.autorepair.shop.domain.workplace;

import hr.autorepair.shop.domain.workplace.dto.AddWorkplaceRequest;
import hr.autorepair.shop.domain.workplace.dto.UpdateWorkplaceRequest;
import hr.autorepair.shop.domain.workplace.dto.WorkplaceResponse;
import hr.autorepair.shop.domain.workplace.service.WorkplaceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workplace")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
@AllArgsConstructor
public class WorkplaceController {

    private final WorkplaceService workplaceService;

    @GetMapping
    public ResponseEntity<List<WorkplaceResponse>> getWorkplaces(){
        return ResponseEntity.ok(workplaceService.getWorkplaces());
    }

    @GetMapping("/{idWorkplace}")
    public ResponseEntity<WorkplaceResponse> getWorkplace(@PathVariable Long idWorkplace){
        return ResponseEntity.ok(workplaceService.getWorkplace(idWorkplace));
    }

    @PostMapping
    public ResponseEntity<WorkplaceResponse> addWorkplace(@RequestBody @Valid AddWorkplaceRequest request){
        return ResponseEntity.ok(workplaceService.addWorkplace(request));
    }

    @PutMapping("/{idWorkplace}")
    public ResponseEntity<WorkplaceResponse> updateWorkplace(
            @PathVariable Long idWorkplace,
            @RequestBody @Valid UpdateWorkplaceRequest request
    ){
        return ResponseEntity.ok(workplaceService.updateWorkplace(idWorkplace, request));
    }

    @DeleteMapping("/{idWorkplace}")
    public ResponseEntity<WorkplaceResponse> deactivateWorkplace(@PathVariable Long idWorkplace){
        workplaceService.deactivateWorkplace(idWorkplace);
        return ResponseEntity.noContent().build();
    }

}

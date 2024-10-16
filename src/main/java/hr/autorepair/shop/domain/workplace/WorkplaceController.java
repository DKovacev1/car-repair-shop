package hr.autorepair.shop.domain.workplace;

import hr.autorepair.shop.domain.workplace.dto.WorkPlaceResponse;
import hr.autorepair.shop.domain.workplace.service.WorkplaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workplace")
@AllArgsConstructor
public class WorkplaceController {

    private final WorkplaceService workplaceService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<WorkPlaceResponse>> getWorkplaces(){
        return ResponseEntity.ok(workplaceService.getWorkplaces());
    }

}

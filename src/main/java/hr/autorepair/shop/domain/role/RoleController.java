package hr.autorepair.shop.domain.role;

import hr.autorepair.shop.domain.role.dto.RoleResponse;
import hr.autorepair.shop.domain.role.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * Imamo samo 3 role (USER, EMPLOYEE, ADMIN).
     * Ovisno o roli koju korinik ima, vracamo sve, dio ili nijednu rolu
     * @return Lista rola
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<RoleResponse>> getRole(){
        return ResponseEntity.ok(roleService.getRole());
    }

}

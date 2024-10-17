package hr.autorepair.shop.domain.appuser;

import hr.autorepair.shop.domain.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserLookupRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.appuser.dto.UpdateAppUserRequest;
import hr.autorepair.shop.domain.appuser.service.AppUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-user")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<AppUserResponse>> getAppUsers(@ModelAttribute AppUserLookupRequest request){
        return ResponseEntity.ok(appUserService.getAppUsers(request));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Void> addNewAppUser(@RequestBody @Valid AddAppUserRequest request){
        appUserService.addAppUser(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idAppUser}/activate-app-user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<Void> activateAppUser(@PathVariable Long idAppUser){
        appUserService.activateAppUser(idAppUser);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idAppUser}")
    public ResponseEntity<Void> updateAppUser(@PathVariable Long idAppUser, @RequestBody UpdateAppUserRequest request){
        appUserService.updateAppUser(idAppUser, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idAppUser}")
    public ResponseEntity<Void> deactivateAppUser(@PathVariable Long idAppUser){
        appUserService.deactivateAppUser(idAppUser);
        return ResponseEntity.noContent().build();
    }

}

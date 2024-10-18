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

    @GetMapping("/{idAppUser}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<AppUserResponse> getAppUser(@PathVariable Long idAppUser){
        return ResponseEntity.ok(appUserService.getAppUser(idAppUser));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<AppUserResponse> addNewAppUser(@RequestBody @Valid AddAppUserRequest request){
        return ResponseEntity.ok(appUserService.addAppUser(request));
    }

    @PostMapping("/{idAppUser}/activate-app-user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<AppUserResponse> activateAppUser(@PathVariable Long idAppUser){
        return ResponseEntity.ok(appUserService.activateAppUser(idAppUser));
    }

    @PutMapping("/{idAppUser}")
    public ResponseEntity<AppUserResponse> updateAppUser(
            @PathVariable Long idAppUser,
            @RequestBody @Valid UpdateAppUserRequest request
    ){
        return ResponseEntity.ok(appUserService.updateAppUser(idAppUser, request));
    }

    @DeleteMapping("/{idAppUser}")
    public ResponseEntity<Void> deactivateAppUser(@PathVariable Long idAppUser){
        appUserService.deactivateAppUser(idAppUser);
        return ResponseEntity.noContent().build();
    }

}

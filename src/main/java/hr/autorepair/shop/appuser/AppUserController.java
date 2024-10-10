package hr.autorepair.shop.appuser;

import hr.autorepair.shop.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.appuser.service.AppUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-user")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<Void> addNewAppUser(@RequestBody @Valid AddAppUserRequest request){
        appUserService.addAppUser(request);
        return ResponseEntity.ok().build();
    }

}

package hr.autorepair.shop.login;

import hr.autorepair.shop.login.dto.LoginRequest;
import hr.autorepair.shop.login.service.LoginService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequest request){
        loginService.login(request);
        return ResponseEntity.ok().build();
    }

}

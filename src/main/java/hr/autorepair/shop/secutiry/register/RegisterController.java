package hr.autorepair.shop.secutiry.register;

import hr.autorepair.shop.secutiry.register.dto.RegisterRequest;
import hr.autorepair.shop.secutiry.register.service.RegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request){
        registerService.register(request);
        return ResponseEntity.ok().build();
    }

}

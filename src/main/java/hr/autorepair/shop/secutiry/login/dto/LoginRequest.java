package hr.autorepair.shop.secutiry.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "Email mora biti zadan!")
    @Email(message = "Email mora biti ispravnog oblika!")
    private String email;
    @NotEmpty(message = "Lozinka mora biti zadana!")
    private String password;
    private String verificationCode;//nije obavezno
}

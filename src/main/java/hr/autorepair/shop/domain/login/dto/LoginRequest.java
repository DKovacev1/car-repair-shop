package hr.autorepair.shop.domain.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "Email {error.required}")
    @Email(message = "{error.email}")
    private String email;
    @NotEmpty(message = "Password {error.required}")
    private String password;
    private String verificationCode;//optional
}

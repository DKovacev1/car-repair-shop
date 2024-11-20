package hr.autorepair.shop.domain.register.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotEmpty(message = "First name {error.required}")
    private String firstName;
    @NotEmpty(message = "Last name {error.required}")
    private String lastName;
    @NotEmpty(message = "Email {error.required}")
    @Email(message = "{error.email}")
    private String email;
    @NotEmpty(message = "Password {error.required}")
    @Size(min = 8, message = "Password has to contain at least 8 characters!")
    private String password;
}

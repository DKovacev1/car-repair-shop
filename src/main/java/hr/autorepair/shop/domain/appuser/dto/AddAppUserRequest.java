package hr.autorepair.shop.domain.appuser.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddAppUserRequest {
    @NotEmpty(message = "First name {error.required}")
    private String firstName;
    @NotEmpty(message = "Last name {error.required}")
    private String lastName;
    @NotEmpty(message = "Email {error.required}")
    @Email(message = "{error.email}")
    private String email;
    @NotNull(message = "Role {error.required}")
    private Long idRole;
}

package hr.autorepair.shop.domain.appuser.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddAppUserRequest {
    @NotEmpty(message = "Ime mora biti zadano!")
    private String firstName;
    @NotEmpty(message = "Prezime mora biti zadano!")
    private String lastName;
    @NotEmpty(message = "E-mail mora biti zadan!")
    private String email;
    @NotEmpty(message = "Lozinka mora biti zadana!")
    private String password;
}

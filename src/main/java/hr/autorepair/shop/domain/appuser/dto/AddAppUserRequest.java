package hr.autorepair.shop.domain.appuser.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddAppUserRequest {
    @NotEmpty(message = "Ime mora biti zadano!")
    private String firstName;
    @NotEmpty(message = "Prezime mora biti zadano!")
    private String lastName;
    @NotEmpty(message = "E-mail mora biti zadan!")
    private String email;
    @NotNull(message = "Rola mora biti zadana!")
    private Long idRole;
}

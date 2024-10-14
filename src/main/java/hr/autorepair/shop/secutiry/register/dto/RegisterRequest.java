package hr.autorepair.shop.secutiry.register.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotEmpty(message = "Ime mora biti zadano!")
    private String firstName;
    @NotEmpty(message = "Prezime mora biti zadano!")
    private String lastName;
    @NotEmpty(message = "Email mora biti zadan!")
    @Email(message = "Email mora biti ispravnog oblika!")
    private String email;
    @NotEmpty(message = "Lozinka mora biti zadana!")
    @Size(min = 8, message = "Lozinka mora sadrzžavati najmanje 8 znakova!")
    private String password;
}

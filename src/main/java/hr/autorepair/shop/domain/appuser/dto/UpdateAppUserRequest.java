package hr.autorepair.shop.domain.appuser.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Objects;

@Data
public class UpdateAppUserRequest {
    @NotEmpty(message = "First name {error.required}")
    private String firstName;
    @NotEmpty(message = "Last name {error.required}")
    private String lastName;
    @NotEmpty(message = "Email {error.required}")
    @Email(message = "{error.email}")
    private String email;
    private Long idRole;

    @JsonIgnore
    public boolean hasChanges(UpdateAppUserRequest otherRequest){
        return !(Objects.equals(firstName, otherRequest.firstName)
                && Objects.equals(lastName, otherRequest.lastName)
                && Objects.equals(email, otherRequest.email)
                && (idRole == null || Objects.equals(idRole, otherRequest.idRole)));
    }
}

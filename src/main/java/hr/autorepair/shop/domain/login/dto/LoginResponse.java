package hr.autorepair.shop.domain.login.dto;

import hr.autorepair.shop.domain.role.dto.RoleResponse;
import lombok.Data;

@Data
public class LoginResponse {
    private Long idAppUser;
    private String firstName;
    private String lastName;
    private String email;
    private RoleResponse role;
    private String jwt;
    private boolean isValidated;
}

package hr.autorepair.shop.secutiry.login.dto;

import hr.autorepair.shop.role.dto.RoleResponse;
import lombok.Data;

@Data
public class LoginResponse {
    private String firstName;
    private String lastName;
    private String email;
    private RoleResponse role;
    private String jwt;
    private boolean isValidated;
}

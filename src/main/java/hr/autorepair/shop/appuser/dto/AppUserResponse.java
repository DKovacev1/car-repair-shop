package hr.autorepair.shop.appuser.dto;

import hr.autorepair.shop.role.dto.RoleResponse;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AppUserResponse {
    private Long idAppUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp tstamp;
    private RoleResponse role;
}

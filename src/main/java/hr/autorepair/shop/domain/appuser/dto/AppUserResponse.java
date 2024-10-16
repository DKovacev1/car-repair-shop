package hr.autorepair.shop.domain.appuser.dto;

import hr.autorepair.shop.domain.role.dto.RoleResponse;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AppUserResponse {
    private Long idAppUser;
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp tstamp;
    private boolean isActivated;
    private RoleResponse role;
}

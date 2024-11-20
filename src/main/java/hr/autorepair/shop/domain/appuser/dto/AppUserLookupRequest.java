package hr.autorepair.shop.domain.appuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserLookupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Boolean isActivated;
    private Long idRole;
}

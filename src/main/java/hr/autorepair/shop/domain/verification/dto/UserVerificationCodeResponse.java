package hr.autorepair.shop.domain.verification.dto;

import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserVerificationCodeResponse {
    private Long idUserVerificationCode;
    private AppUserResponse appUser;
    private String verificationCode;
    private Timestamp createdAt;
    private Timestamp expiresAt;
}

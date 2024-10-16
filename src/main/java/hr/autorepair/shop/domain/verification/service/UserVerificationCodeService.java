package hr.autorepair.shop.domain.verification.service;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.login.dto.LoginResponse;

public interface UserVerificationCodeService {
    void verifyUser(LoginResponse loginResponse, AppUser appUser, String verificationCode);
}

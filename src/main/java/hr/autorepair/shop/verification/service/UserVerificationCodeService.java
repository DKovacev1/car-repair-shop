package hr.autorepair.shop.verification.service;

import hr.autorepair.shop.appuser.model.AppUser;
import hr.autorepair.shop.secutiry.login.dto.LoginResponse;

public interface UserVerificationCodeService {
    void verifyUser(LoginResponse loginResponse, AppUser appUser, String verificationCode);
}

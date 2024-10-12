package hr.autorepair.shop.verification.service;

import hr.autorepair.shop.appuser.model.AppUser;

public interface UserVerificationCodeService {
    void verifyUser(AppUser appUser, String verificationCode);
}

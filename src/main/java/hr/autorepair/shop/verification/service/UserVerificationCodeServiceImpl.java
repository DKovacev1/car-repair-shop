package hr.autorepair.shop.verification.service;

import hr.autorepair.common.utils.VerificationCodeUtil;
import hr.autorepair.shop.appuser.model.AppUser;
import hr.autorepair.shop.verification.model.UserVerificationCode;
import hr.autorepair.shop.verification.repository.UserVerificationCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class UserVerificationCodeServiceImpl implements UserVerificationCodeService{

    private final UserVerificationCodeRepository userVerificationCodeRepository;

    private static final Integer CODE_EXPIRATION_TIME = 120; //u sekundama
    private static final Integer CODE_LENGTH = 6; //u sekundama

    @Override
    public void verifyUser(AppUser appUser, String verificationCode) {
        long now = System.currentTimeMillis();
        Timestamp nowTime = new Timestamp(now);

        if((verificationCode == null || verificationCode.isEmpty()) //ako ne postoji nikakav kod u zadnjih 120 sekundi, napravi novi
            && !userVerificationCodeRepository.isCodeCreated(appUser.getIdAppUser(), nowTime)){
            long nowExtended = now + (CODE_EXPIRATION_TIME * 1000);
            Timestamp nowExtendedTime = new Timestamp(nowExtended);

            UserVerificationCode userVerificationCode = new UserVerificationCode();
            userVerificationCode.setAppUser(appUser);
            userVerificationCode.setVerificationCode(VerificationCodeUtil.generateVerificationCode(CODE_LENGTH).toString());
            userVerificationCode.setCreatedAt(nowTime);
            userVerificationCode.setExpiresAt(nowExtendedTime);

            userVerificationCodeRepository.save(userVerificationCode);
        }



    }



}

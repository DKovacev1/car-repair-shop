package hr.autorepair.shop.verification.service;

import hr.autorepair.common.utils.VerificationCodeUtil;
import hr.autorepair.shop.appuser.model.AppUser;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.secutiry.login.dto.LoginResponse;
import hr.autorepair.shop.util.AppProperties;
import hr.autorepair.shop.util.MailUtility;
import hr.autorepair.shop.verification.model.UserVerificationCode;
import hr.autorepair.shop.verification.repository.UserVerificationCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.MessageFormat;

import static hr.autorepair.common.constants.MailConstants.VERIFICATION_MAIL_BODY;
import static hr.autorepair.common.constants.MailConstants.ACTIVATION_MAIL_SUBJECT;
import static hr.autorepair.common.constants.MessageConstants.CODRE_ERROR_MESSAGE;

@Service
@AllArgsConstructor
public class UserVerificationCodeServiceImpl implements UserVerificationCodeService{

    private final UserVerificationCodeRepository userVerificationCodeRepository;
    private final AppProperties appProperties;
    private final MailUtility mailUtility;
    private final JavaMailSender javaMailSender;


    @Override
    public void verifyUser(LoginResponse loginResponse, AppUser appUser, String verificationCode) {
        long now = System.currentTimeMillis();
        Timestamp nowTime = new Timestamp(now);
        String jwt = loginResponse.getJwt();

        loginResponse.setValidated(false);
        loginResponse.setJwt(null);

        //ako ne postoji verificationCode u zadnjih x minuta, posalji novi
        if(!userVerificationCodeRepository.isCodeCreated(appUser.getIdAppUser(), nowTime)){
            long nowExtended = now + (Integer.parseInt(appProperties.getProperty("app.code.expiration")) * 1000L);
            Timestamp nowExtendedTime = new Timestamp(nowExtended);

            UserVerificationCode userVerificationCode = new UserVerificationCode();
            userVerificationCode.setAppUser(appUser);
            userVerificationCode.setVerificationCode(VerificationCodeUtil.generateVerificationCode(Integer.parseInt(appProperties.getProperty("app.code.length"))).toString());
            userVerificationCode.setCreatedAt(nowTime);
            userVerificationCode.setExpiresAt(nowExtendedTime);

            userVerificationCodeRepository.save(userVerificationCode);

            SimpleMailMessage message = mailUtility.getSimpleMailMessage();
            message.setTo(appUser.getEmail());
            message.setSubject(ACTIVATION_MAIL_SUBJECT);
            message.setText(MessageFormat.format(VERIFICATION_MAIL_BODY, appUser.getEmail(), userVerificationCode.getVerificationCode()));

            javaMailSender.send(message);
        }
        else{ //ako postoji, provjeri ga
            UserVerificationCode userVerificationCode = userVerificationCodeRepository.getUserVerificationCodeForUserAtTime(appUser.getIdAppUser(), nowTime)
                    .orElseThrow(() -> new BadRequestException(CODRE_ERROR_MESSAGE));

            if(verificationCode != null && !verificationCode.isEmpty() && verificationCode.equals(userVerificationCode.getVerificationCode())){
                loginResponse.setValidated(true);//ako je sve oke, dizemo flag validated i vracamo token nazad
                loginResponse.setJwt(jwt);
            }
            else
                throw new BadRequestException(CODRE_ERROR_MESSAGE);
        }

    }

}
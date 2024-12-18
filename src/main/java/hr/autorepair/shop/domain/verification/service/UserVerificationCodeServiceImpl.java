package hr.autorepair.shop.domain.verification.service;

import hr.autorepair.common.utils.VerificationCodeUtil;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.login.dto.LoginResponse;
import hr.autorepair.shop.domain.verification.model.UserVerificationCode;
import hr.autorepair.shop.domain.verification.repository.UserVerificationCodeRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.util.AppProperties;
import hr.autorepair.shop.util.MailUtility;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.MessageFormat;

import static hr.autorepair.common.constants.MailConstants.VERIFICATION_MAIL_BODY;
import static hr.autorepair.common.constants.MailConstants.VERIFICATION_MAIL_SUBJECT;
import static hr.autorepair.common.constants.MessageConstants.CODE_ERROR_MESSAGE;

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

        //if user verification code does not exist in the past x minutes, send a new one
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
            message.setSubject(VERIFICATION_MAIL_SUBJECT);
            message.setText(MessageFormat.format(VERIFICATION_MAIL_BODY, appUser.getEmail(), userVerificationCode.getVerificationCode()));

            javaMailSender.send(message);
        }
        else{//if exists, validate it
            UserVerificationCode userVerificationCode = userVerificationCodeRepository.getUserVerificationCodeForUserAtTime(appUser.getIdAppUser(), nowTime)
                    .orElseThrow(() -> new BadRequestException(CODE_ERROR_MESSAGE));

            if(verificationCode != null && !verificationCode.isEmpty() && verificationCode.equals(userVerificationCode.getVerificationCode())){
                loginResponse.setValidated(true);//if everything is ok, we raise the flag and return the token
                loginResponse.setJwt(jwt);
            }
            else
                throw new BadRequestException(CODE_ERROR_MESSAGE);
        }

    }

}
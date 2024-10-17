package hr.autorepair.shop.domain.login.service;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.domain.role.dto.RoleResponse;
import hr.autorepair.shop.domain.role.util.RoleEnum;
import hr.autorepair.shop.domain.login.dto.LoginRequest;
import hr.autorepair.shop.domain.login.dto.LoginResponse;
import hr.autorepair.shop.util.AppProperties;
import hr.autorepair.shop.util.JwtUtil;
import hr.autorepair.shop.domain.verification.service.UserVerificationCodeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static hr.autorepair.common.constants.MessageConstants.LOGIN_NOT_ALLOWED;
import static hr.autorepair.common.constants.MessageConstants.WRONG_CREDENTIALS;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AppUserRepository appUserRepository;
    private final UserVerificationCodeService userVerificationCodeService;
    private final JwtUtil jwtUtil;
    private final AppProperties appProperties;
    private final ModelMapper modelMapper;



    @Override
    public LoginResponse login(LoginRequest request) {
        AppUser appUser = appUserRepository.findByEmailAndIsDeletedFalseAndIsActivatedTrue(request.getEmail())
                .orElseThrow(() -> new BadRequestException(WRONG_CREDENTIALS));

        if(!PasswordUtil.isPasswordMatching(request.getPassword(), appUser.getPassword()))
            throw new BadRequestException(WRONG_CREDENTIALS);

        if(Boolean.FALSE.equals(appUser.getIsActivated()))
            throw new BadRequestException(LOGIN_NOT_ALLOWED);

        RoleResponse roleResponse = modelMapper.map(appUser.getRole(), RoleResponse.class);
        String jwt = jwtUtil.generateToken(appUser.getIdAppUser(), appUser.getEmail(), roleResponse);
        LoginResponse loginResponse = modelMapper.map(appUser, LoginResponse.class);
        loginResponse.setJwt(jwt);
        loginResponse.setValidated(true);

        if(appUser.getRole().getName().equals(RoleEnum.ADMIN.getName())
            && Boolean.parseBoolean(appProperties.getProperty("app.validate.admin")))
            userVerificationCodeService.verifyUser(loginResponse, appUser, request.getVerificationCode());

        return loginResponse;
    }

}

package hr.autorepair.shop.login.service;

import hr.autorepair.common.utils.JwtUtil;
import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.appuser.model.AppUser;
import hr.autorepair.shop.appuser.repository.AppUserRepository;
import hr.autorepair.shop.login.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final AppUserRepository appUserRepository;

    private static final String WRONG_CREDENTIALS = "Pogrešni podaci za prijavu!";

    @Override
    public void login(LoginRequest request) {
        AppUser appUser = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException(WRONG_CREDENTIALS));

        if(PasswordUtil.isPasswordMatching(request.getPassword(), appUser.getPassword())){
            String token = JwtUtil.generateToken(request.getEmail());
        }
        else
            throw new BadRequestException(WRONG_CREDENTIALS);

    }

}

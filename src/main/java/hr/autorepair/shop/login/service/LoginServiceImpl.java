package hr.autorepair.shop.login.service;

import hr.autorepair.common.utils.JwtUtil;
import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.appuser.model.AppUser;
import hr.autorepair.shop.appuser.repository.AppUserRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.login.dto.LoginRequest;
import hr.autorepair.shop.login.dto.LoginResponse;
import hr.autorepair.shop.role.dto.RoleResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    private static final String WRONG_CREDENTIALS = "Pogrešni podaci za prijavu!";

    @Override
    public LoginResponse login(LoginRequest request) {
        AppUser appUser = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException(WRONG_CREDENTIALS));

        if(!PasswordUtil.isPasswordMatching(request.getPassword(), appUser.getPassword()))
            throw new BadRequestException(WRONG_CREDENTIALS);

        RoleResponse roleResponse = modelMapper.map(appUser.getRole(), RoleResponse.class);
        String jwt = JwtUtil.generateToken(appUser.getIdAppUser(), appUser.getEmail(), roleResponse);

        LoginResponse loginResponse = modelMapper.map(appUser, LoginResponse.class);
        loginResponse.setJwt(jwt);

        return loginResponse;
    }

}

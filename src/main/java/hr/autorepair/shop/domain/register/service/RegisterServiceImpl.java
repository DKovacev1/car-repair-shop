package hr.autorepair.shop.domain.register.service;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.domain.role.model.Role;
import hr.autorepair.shop.domain.role.repository.RoleRepository;
import hr.autorepair.shop.domain.role.util.RoleEnum;
import hr.autorepair.shop.domain.register.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public void register(RegisterRequest request) {
        if(appUserRepository.findByEmail(request.getEmail()).isPresent())
            throw new BadRequestException("Korisnik sa mail adresom '" + request.getEmail() + "' je već prijavljen u sustav!");

        //TODO provjera postojanja mail-a

        AppUser appUser = modelMapper.map(request, AppUser.class);
        appUser.setPassword(PasswordUtil.getEncodedPassword(request.getPassword()));
        appUser.setTstamp(new Timestamp(System.currentTimeMillis()));
        appUser.setIsActivated(false);
        Role role = roleRepository.findByName(RoleEnum.USER.getName())
                        .orElseThrow(() -> new BadRequestException("Ne posotji rola sa nazivom " + RoleEnum.USER.getName() + "."));
        appUser.setRole(role);

        appUserRepository.save(appUser);
    }

}

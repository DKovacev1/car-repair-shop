package hr.autorepair.shop.appuser.service;

import hr.autorepair.shop.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.appuser.model.AppUser;
import hr.autorepair.shop.appuser.repository.AppUserRepository;
import hr.autorepair.shop.role.model.Role;
import hr.autorepair.shop.role.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public void addAppUser(AddAppUserRequest request) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setEmail(request.getEmail());//TODO -> validacije
        appUser.setPassword(request.getPassword());//TODO -> enkripcija
        appUser.setTstamp(new Timestamp(System.currentTimeMillis()));
        Role role = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new BadRequestException("Ne postoji rola koja se zove ADMIN."));
        appUser.setRole(role);

        appUserRepository.save(appUser);
    }

}

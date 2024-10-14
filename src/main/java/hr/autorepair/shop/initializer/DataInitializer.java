package hr.autorepair.shop.initializer;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.appuser.model.AppUser;
import hr.autorepair.shop.appuser.repository.AppUserRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.role.model.Role;
import hr.autorepair.shop.role.repository.RoleRepository;
import hr.autorepair.shop.role.util.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.save(role);

        role = new Role();
        role.setName("USER");
        roleRepository.save(role);

        AppUser appUser = new AppUser();
        appUser.setFirstName("Damjan");
        appUser.setLastName("Kovacev");
        appUser.setEmail("damjan356@gmail.com");
        appUser.setPassword(PasswordUtil.getEncodedPassword("NekiPassword"));
        appUser.setTstamp(new Timestamp(System.currentTimeMillis()));
        String rola = RoleEnum.ADMIN.getName();
        role = roleRepository.findByName(rola)
                .orElseThrow(() -> new BadRequestException("Ne postoji rola koja se zove " + rola + "."));
        appUser.setRole(role);
        appUser.setIsActivated(true);

        appUserRepository.save(appUser);
    }

}

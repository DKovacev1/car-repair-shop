package hr.autorepair.shop.initializer;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.appuser.service.AppUserService;
import hr.autorepair.shop.role.model.Role;
import hr.autorepair.shop.role.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final AppUserService appUserService;
    private final JavaMailSender javaMailSender;

    @Override
    public void run(ApplicationArguments args) {
        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.save(role);

        role = new Role();
        role.setName("USER");
        roleRepository.save(role);

        AddAppUserRequest request = new AddAppUserRequest();
        request.setFirstName("Damjan");
        request.setLastName("Kovacev");
        request.setEmail("damjan356@gmail.com");
        request.setPassword(PasswordUtil.getEncodedPassword("NekiPassword"));

        appUserService.addAppUser(request);

        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("damjan356@gmail.com");
        message.setSubject("PROBA - NASLOV");
        message.setText("Pozdrav sa beka");
        message.setFrom("info.repair.shop.hr@gmail.com");
        javaMailSender.send(message);*/
    }

}

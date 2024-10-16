package hr.autorepair.shop.domain.appuser.service;

import hr.autorepair.shop.domain.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserLookupRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.domain.role.model.Role;
import hr.autorepair.shop.util.MailUtility;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static hr.autorepair.common.constants.MailConstants.*;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;
    private final JavaMailSender javaMailSender;
    private final MailUtility mailUtility;
    private final ModelMapper modelMapper;

    @Override
    public List<AppUserResponse> getAppUsers(AppUserLookupRequest request) {
        return appUserRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<AppUser, Role> appUserRoleJoin = root.join("role");

            if(request.getFirstName() != null && !request.getFirstName().isEmpty())
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + request.getFirstName().toLowerCase() + "%"));
            if(request.getLastName() != null && !request.getLastName().isEmpty())
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + request.getLastName().toLowerCase() + "%"));
            if(request.getEmail() != null && !request.getEmail().isEmpty())
                predicates.add(criteriaBuilder.equal(root.get("email"), request.getEmail()));
            if(request.getIsActivated() != null)
                predicates.add(criteriaBuilder.equal(root.get("isActivated"), request.getIsActivated()));
            if(request.getIdRole() != null)
                predicates.add(criteriaBuilder.equal(appUserRoleJoin.get("idRole"), request.getIdRole()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }).stream()
                .map(appUser -> modelMapper.map(appUser, AppUserResponse.class))
                .toList();
    }

    @Override
    public void addAppUser(AddAppUserRequest request) {

    }

    @Override
    public void activateAppUser(Long idAppUser) {
        appUserRepository.findById(idAppUser)
                .ifPresentOrElse(appUser -> {
                    appUser.setIsActivated(true);
                    appUserRepository.save(appUser);

                    SimpleMailMessage message = mailUtility.getSimpleMailMessage();
                    message.setTo(appUser.getEmail());
                    message.setSubject(ACTIVATION_MAIL_SUBJECT);
                    message.setText(MessageFormat.format(ACTIVATION_MAIL_BODY, appUser.getEmail()));
                    javaMailSender.send(message);
                }, () -> {
                    throw new BadRequestException("Ne postoji korisnik sa šifrom " + idAppUser + ".");
                });
    }

}

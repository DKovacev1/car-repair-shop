package hr.autorepair.shop.domain.appuser.service;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.domain.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserLookupRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.domain.role.repository.RoleRepository;
import hr.autorepair.shop.domain.role.util.RoleUtil;
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
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static hr.autorepair.common.constants.MailConstants.*;
import static hr.autorepair.common.constants.MessageConstants.*;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;
    private final JavaMailSender javaMailSender;
    private final MailUtility mailUtility;
    private final RoleUtil roleUtil;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

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
    @Transactional
    public void addAppUser(AddAppUserRequest request) {
        if(appUserRepository.findByEmail(request.getEmail()).isPresent())
            throw new BadRequestException(MessageFormat.format(EMAIL_ALREADY_IN_USE, request.getEmail()));

        if(!roleUtil.hasAccessToRole(request.getIdRole()))
            throw new BadRequestException(MessageFormat.format(ROLE_ID_NOT_EXIST, request.getIdRole()));

        if(!mailUtility.emailAddressExist())
            throw new BadRequestException(MessageFormat.format(EMAIL_NOT_EXIST, request.getEmail()));

        Role role = roleRepository.findById(request.getIdRole())
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(ROLE_ID_NOT_EXIST, request.getIdRole())));
        String randomPassword = PasswordUtil.generateRandomPassword();

        AppUser appUser = modelMapper.map(request, AppUser.class);
        appUser.setPassword(PasswordUtil.getEncodedPassword(randomPassword));
        appUser.setTstamp(new Timestamp(System.currentTimeMillis()));
        appUser.setIsActivated(true);
        appUser.setRole(role);
        appUserRepository.save(appUser);

        SimpleMailMessage message = mailUtility.getSimpleMailMessage();
        message.setTo(appUser.getEmail());
        message.setSubject(USER_CREATED_MAIL_SUBJECT);
        message.setText(MessageFormat.format(USER_CREATED_MAIL_BODY, appUser.getEmail(), randomPassword));
        javaMailSender.send(message);
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

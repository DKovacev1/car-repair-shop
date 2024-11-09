package hr.autorepair.shop.domain.appuser.service;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.domain.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserLookupRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.appuser.dto.UpdateAppUserRequest;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.domain.role.model.Role;
import hr.autorepair.shop.domain.role.repository.RoleRepository;
import hr.autorepair.shop.domain.role.util.RoleUtil;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.util.MailUtility;
import hr.autorepair.shop.util.UserDataUtils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));//we are showing only active users
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
    public AppUserResponse addAppUser(AddAppUserRequest request) {
        if(appUserRepository.findByEmailAndIsDeletedFalseAndIsActivatedTrue(request.getEmail()).isPresent())
            throw new BadRequestException(MessageFormat.format(EMAIL_ALREADY_IN_USE, request.getEmail()));

        if(!mailUtility.emailAddressExist(request.getEmail()))
            throw new BadRequestException(MessageFormat.format(EMAIL_NOT_EXIST, request.getEmail()));

        if(!roleUtil.hasAccessToRole(request.getIdRole()))
            throw new BadRequestException(MessageFormat.format(ROLE_ID_NOT_EXIST, request.getIdRole()));

        Role role = roleRepository.findById(request.getIdRole())
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(ROLE_ID_NOT_EXIST, request.getIdRole())));
        String randomPassword = PasswordUtil.generateRandomPassword();

        AppUser appUser = modelMapper.map(request, AppUser.class);
        appUser.setPassword(PasswordUtil.getEncodedPassword(randomPassword));
        appUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        appUser.setIsActivated(true);
        appUser.setIsDeleted(false);
        appUser.setRole(role);
        appUserRepository.save(appUser);

        SimpleMailMessage message = mailUtility.getSimpleMailMessage();
        message.setTo(appUser.getEmail());
        message.setSubject(USER_CREATED_MAIL_SUBJECT);
        message.setText(MessageFormat.format(USER_CREATED_MAIL_BODY, appUser.getEmail(), randomPassword));
        javaMailSender.send(message);

        return modelMapper.map(appUser, AppUserResponse.class);
    }

    @Override
    public AppUserResponse activateAppUser(Long idAppUser) {
        AppUser appUser = appUserRepository.findById(idAppUser)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(USER_NOT_EXIST, idAppUser)));

        appUser.setIsActivated(true);
        appUserRepository.save(appUser);

        SimpleMailMessage message = mailUtility.getSimpleMailMessage();
        message.setTo(appUser.getEmail());
        message.setSubject(ACTIVATION_MAIL_SUBJECT);
        message.setText(MessageFormat.format(ACTIVATION_MAIL_BODY, appUser.getEmail()));
        javaMailSender.send(message);

        return modelMapper.map(appUser, AppUserResponse.class);
    }

    @Override
    public void deactivateAppUser(Long idAppUser) {
        appUserRepository.findById(idAppUser)
                .ifPresentOrElse(appUser -> {
                    appUser.setIsDeleted(true);
                    appUserRepository.save(appUser);
                }, () -> {
                    throw new BadRequestException(MessageFormat.format(USER_NOT_EXIST, idAppUser));
                });
    }

    @Override
    public AppUserResponse updateAppUser(Long idAppUser, UpdateAppUserRequest request) {
        AppUser appUser = appUserRepository.findById(idAppUser)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(USER_NOT_EXIST, idAppUser)));

        if(!request.hasChanges(modelMapper.map(appUser, UpdateAppUserRequest.class)))
            throw new BadRequestException(NO_CHANGES_MADE);

        appUserRepository.findByEmailAndIsDeletedFalseAndIsActivatedTrue(request.getEmail())
                .ifPresent(appUserByEmail -> {
                    if(!idAppUser.equals(appUserByEmail.getIdAppUser()))//only if different user has that mail, then throw exception
                        throw new BadRequestException(MessageFormat.format(EMAIL_ALREADY_IN_USE, request.getEmail()));
                });

        if(!mailUtility.emailAddressExist(request.getEmail()))
            throw new BadRequestException(MessageFormat.format(EMAIL_NOT_EXIST, request.getEmail()));

        //admin and employee can change role, regular user cannot
        if(!UserDataUtils.getUserPrincipal().isUser() && request.getIdRole() != null){
            if(!roleUtil.hasAccessToRole(request.getIdRole()))
                throw new BadRequestException(MessageFormat.format(ROLE_ID_NOT_EXIST, request.getIdRole()));

            Role role = roleRepository.findById(request.getIdRole())
                    .orElseThrow(() -> new BadRequestException(MessageFormat.format(ROLE_ID_NOT_EXIST, request.getIdRole())));
            appUser.setRole(role);
        }

        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setEmail(request.getEmail());
        appUserRepository.save(appUser);

        return modelMapper.map(appUser, AppUserResponse.class);
    }

    @Override
    public AppUserResponse getAppUser(Long idAppUser) {
        AppUser appUser = appUserRepository.findByIdAppUserAndIsDeletedFalse(idAppUser)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(USER_NOT_EXIST, idAppUser)));
        return modelMapper.map(appUser, AppUserResponse.class);
    }

}

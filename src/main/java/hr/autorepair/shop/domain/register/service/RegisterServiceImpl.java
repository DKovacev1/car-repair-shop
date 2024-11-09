package hr.autorepair.shop.domain.register.service;

import hr.autorepair.common.utils.PasswordUtil;
import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.appuser.repository.AppUserRepository;
import hr.autorepair.shop.exception.exceptions.BadRequestException;
import hr.autorepair.shop.domain.role.model.Role;
import hr.autorepair.shop.domain.role.repository.RoleRepository;
import hr.autorepair.shop.domain.role.util.RoleEnum;
import hr.autorepair.shop.domain.register.dto.RegisterRequest;
import hr.autorepair.shop.util.MailUtility;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.MessageFormat;

import static hr.autorepair.common.constants.MessageConstants.*;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final MailUtility mailUtility;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        if(appUserRepository.findByEmailAndIsDeletedFalseAndIsActivatedTrue(request.getEmail()).isPresent())
            throw new BadRequestException(MessageFormat.format(EMAIL_ALREADY_IN_USE, request.getEmail()));

        if(!mailUtility.emailAddressExist(request.getEmail()))
            throw new BadRequestException(MessageFormat.format(EMAIL_NOT_EXIST, request.getEmail()));

        AppUser appUser = modelMapper.map(request, AppUser.class);
        appUser.setPassword(PasswordUtil.getEncodedPassword(request.getPassword()));
        appUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        appUser.setIsActivated(false);
        Role role = roleRepository.findByName(RoleEnum.USER.getName())
                .orElseThrow(() -> new BadRequestException(MessageFormat.format(ROLE_NAME_NOT_EXIST, RoleEnum.USER.getName())));
        appUser.setRole(role);

        appUserRepository.save(appUser);
    }



}

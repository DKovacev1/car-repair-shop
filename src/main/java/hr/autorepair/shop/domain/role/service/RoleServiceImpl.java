package hr.autorepair.shop.domain.role.service;

import hr.autorepair.shop.domain.role.dto.RoleResponse;
import hr.autorepair.shop.domain.role.repository.RoleRepository;
import hr.autorepair.shop.domain.role.util.RoleEnum;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoleResponse> getRole() {
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isAdmin())//admin gets all the roles
            return roleRepository.findAll().stream()
                    .map(role -> modelMapper.map(role, RoleResponse.class))
                    .toList();
        else if (userPrincipal.isEmployee())//employee gets all roles except admin
            return roleRepository.findByNameNot(RoleEnum.ADMIN.getName()).stream()
                    .map(role -> modelMapper.map(role, RoleResponse.class))
                    .toList();
        else
            return List.of();//regular user gets none
    }

}

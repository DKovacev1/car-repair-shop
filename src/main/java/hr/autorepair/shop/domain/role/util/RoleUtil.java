package hr.autorepair.shop.domain.role.util;

import hr.autorepair.shop.domain.role.model.Role;
import hr.autorepair.shop.domain.role.repository.RoleRepository;
import hr.autorepair.shop.secutiry.model.UserPrincipal;
import hr.autorepair.shop.util.UserDataUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleUtil {

    private final RoleRepository roleRepository;

    public boolean hasAccessToRole(Long idRole){
        UserPrincipal userPrincipal = UserDataUtils.getUserPrincipal();
        if(userPrincipal.isAdmin())//admin has access to all roles
            return roleRepository.findAll().stream()
                    .map(Role::getIdRole)
                    .toList().contains(idRole);
        else if (userPrincipal.isEmployee())//employee has access to all roles except admin
            return roleRepository.findByNameNot(RoleEnum.ADMIN.getName()).stream()
                    .map(Role::getIdRole)
                    .toList().contains(idRole);
        else
            return false;//regular user has no access to any role
    }

}

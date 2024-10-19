package hr.autorepair.shop.secutiry.model;

import hr.autorepair.shop.domain.appuser.model.AppUser;
import hr.autorepair.shop.domain.role.util.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private AppUser appUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(appUser.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return appUser.getEmail();
    }

    public boolean isUser(){
        return appUser.getRole().getName().equals(RoleEnum.USER.getName());
    }

    public boolean isEmployee(){
        return appUser.getRole().getName().equals(RoleEnum.EMPLOYEE.getName());
    }

    public boolean isAdmin(){
        return appUser.getRole().getName().equals(RoleEnum.ADMIN.getName());
    }

}

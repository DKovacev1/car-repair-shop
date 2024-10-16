package hr.autorepair.shop.secutiry.model;

import hr.autorepair.shop.domain.role.dto.RoleResponse;
import hr.autorepair.shop.domain.role.util.RoleEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@Data
public class UserPrincipal implements UserDetails {

    private Long idAppUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp tstamp;
    private RoleResponse role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public boolean isUser(){
        return role.getName().equals(RoleEnum.USER.getName());
    }

    public boolean isEmployee(){
        return role.getName().equals(RoleEnum.EMPLOYEE.getName());
    }

    public boolean isAdmin(){
        return role.getName().equals(RoleEnum.ADMIN.getName());
    }

}

package hr.autorepair.shop.util;

import hr.autorepair.shop.secutiry.model.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserDataUtils {
    private UserDataUtils(){}

    public static UserPrincipal getUserPrincipal(){
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

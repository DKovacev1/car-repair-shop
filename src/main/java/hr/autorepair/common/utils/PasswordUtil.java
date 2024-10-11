package hr.autorepair.common.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

    public static boolean isPasswordMatching(String password, String encodedPassword){
        return passwordEncoder.matches(password, encodedPassword);
    }

}

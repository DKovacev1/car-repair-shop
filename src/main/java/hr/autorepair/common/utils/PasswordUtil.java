package hr.autorepair.common.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

import static hr.autorepair.common.constants.BusinessConstants.PASSWORD_MIN_LENGTH;

public class PasswordUtil {
    private PasswordUtil(){}

    private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private static final SecureRandom random = new SecureRandom();
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!#._"; //!@#$%^&*()_+{}[]
    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL_CHARS;

    public static String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

    public static boolean isPasswordMatching(String password, String encodedPassword){
        return passwordEncoder.matches(password, encodedPassword);
    }

    public static String generateRandomPassword(){
        StringBuilder password = new StringBuilder(PASSWORD_MIN_LENGTH);
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        for (int i = 4; i < PASSWORD_MIN_LENGTH; i++)
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));

        return password.toString();
    }

}

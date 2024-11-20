package hr.autorepair.common.utils;

import java.util.Random;

public class VerificationCodeUtil {

    public static Integer generateVerificationCode(Integer length) {
        Random random = new Random();
        int code = 0;
        code = random.nextInt((int) Math.pow(10, length));
        return code;
    }

}

package hr.autorepair.common.constants;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class BusinessConstants {
    private BusinessConstants(){}
    public static final Integer PASSWORD_MIN_LENGTH = 8;
    public static final LocalTime OPENING_TIME = LocalTime.of(8,0);
    public static final LocalTime CLOSING_TIME = LocalTime.of(16,0);
    public static final Duration SLOT_DURATION = Duration.ofMinutes(30);
    public static final Integer MIN_LOYALTY_JOB_ORDER_COUNT = 5;
    public static final BigDecimal LOYALTY_JOB_ORDER_DISCOUNT = BigDecimal.valueOf(0.05);
}

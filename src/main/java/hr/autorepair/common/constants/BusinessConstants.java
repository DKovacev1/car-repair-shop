package hr.autorepair.common.constants;

import java.time.Duration;
import java.time.LocalTime;

public class BusinessConstants {
    private BusinessConstants(){}
    public static final Integer PASSWORD_MIN_LENGTH = 8;
    public static final LocalTime OPENING_TIME = LocalTime.of(8,0);
    public static final LocalTime CLOSING_TIME = LocalTime.of(16,0);
    public static final Duration SLOT_DURATION = Duration.ofMinutes(30);
}

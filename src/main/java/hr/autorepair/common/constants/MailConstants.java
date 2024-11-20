package hr.autorepair.common.constants;

public class MailConstants {
    private MailConstants(){}
    public static final String VERIFICATION_MAIL_SUBJECT = "Your sign in request";
    public static final String ACTIVATION_MAIL_SUBJECT = "Your sign up request";
    public static final String USER_CREATED_MAIL_SUBJECT = "Your user data";
    public static final String VEHICLE_SERVICED_MAIL_SUBJECT = "Car service finished";

    public static final String VERIFICATION_MAIL_BODY = """
        Dear user {0},
        
        We have received sign in request.
        
        Your one-time code is: {1}
        """;
    public static final String ACTIVATION_MAIL_BODY = """
        Dear user {0},
        
        Your sign up request has been accepted.
        
        You can use the application.
        """;
    public static final String USER_CREATED_MAIL_BODY = """
        Dear user {0},
        
        Your sign in data is:
            - email: {0}
            - password: {1}
        
        You can sign into the application.
        """;
    public static final String VEHICLE_SERVICED_MAIL_BODY = """
        Dear user {0},
        
        Your car has been serviced.
        
        You can come and pick it up.
        """;
}

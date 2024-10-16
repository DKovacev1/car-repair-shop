package hr.autorepair.common.constants;

public class MailConstants {
    private MailConstants(){}
    public static final String VERIFICATION_MAIL_SUBJECT = "Vaš zahtjev za prijavu";
    public static final String ACTIVATION_MAIL_SUBJECT = "Vaš zahtjev za registraciju";
    public static final String USER_CREATED_MAIL_SUBJECT = "Vaši korisnički podaci";

    public static final String VERIFICATION_MAIL_BODY = """
        Poštovani korisniče {0},
        
        Primili smo zahtjev za prijavu u sustav.
        
        Vaš jednokratni kod je: {1}
        """;
    public static final String ACTIVATION_MAIL_BODY = """
        Poštovani korisniče {0},
        
        Vaš zahtjev za registraciju u sustav je odobren.
        
        Možete se prijaviti u aplikaciju.
        """;
    public static final String USER_CREATED_MAIL_BODY = """
        Poštovani korisniče {0},
        
        Vaši korisnički podaci za prijavu u sustav su:
            - email: {0}
            - lozinka: {1}
        
        Možete se prijaviti u aplikaciju.
        """;
}

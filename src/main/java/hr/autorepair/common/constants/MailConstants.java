package hr.autorepair.common.constants;

public class MailConstants {
    public static final String VERIFICATION_MAIL_SUBJECT = "Vaš zahtjev za prijavu";
    public static final String ACTIVATION_MAIL_SUBJECT = "Vaš zahtjev za registraciju";

    public static final String VERIFICATION_MAIL_BODY = """
        <p>Poštovani korisniče <strong>{0}</strong>,</p>
        
        <p>Primili smo zahtjev za prijavu u sustav.</p>
        
        <p>Vaš jednokratni kod je: <strong>{1}</strong></p>
        """;
    public static final String ACTIVATION_MAIL_BODY = """
        <p>Poštovani korisniče <strong>{0}</strong>,</p>
        
        <p>Vaš zahtjev za registraciju u sustav je odobren.</p>
        
        <p>Možete se prijaviti u aplikaciju.</p>
        """;
}

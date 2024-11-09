package hr.autorepair.shop.util;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailUtility {

    private final AppProperties appProperties;
    private final JavaMailSender mailSender;

    public SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(appProperties.getProperty("spring.mail.username"));
        return simpleMailMessage;
    }

    /**
     *
     * @param subject mail subject
     * @param mailTo mail address where to send
     * @param @param mailBody body that is going to be sent in email in order to check if the mail exists
     * @return true - mail is sent, email exists ||| false - mail not sent, mail does not exits
     */
    public boolean sendEmail(String subject, String mailTo, String mailBody){
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setText(mailBody);


        //TODO sibni u chatgpt sve ovo, i sam mu reci da ak mail ne postoji da sibne false, inace vrati true ako se poslalo
        return true;//za sada pusta sve
    }

}

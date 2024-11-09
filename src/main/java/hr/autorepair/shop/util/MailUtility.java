package hr.autorepair.shop.util;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailUtility {

    private final AppProperties appProperties;
    private final JavaMailSender mailSender;

    // Method to create a basic SimpleMailMessage template
    public SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(appProperties.getProperty("spring.mail.username"));
        return simpleMailMessage;
    }

    /**
     * Attempts to send an email to the specified address.
     *
     * @param subject - Subject of the email
     * @param mailTo - Recipient's email address
     * @param mailBody - Body of the email
     * @return true if the email was sent successfully, false if the email could not be sent
     */
    public boolean sendEmail(String subject, String mailTo, String mailBody) {
        try {
            // Prepare the email message
            SimpleMailMessage simpleMailMessage = getSimpleMailMessage();
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setTo(mailTo);
            simpleMailMessage.setText(mailBody);

            // Attempt to send the email
            mailSender.send(simpleMailMessage);
            System.out.println("Email sent successfully to: " + mailTo);
            return true;
        } catch (MailSendException e) {
            System.err.println("Invalid email address: " + mailTo);
            return false;
        } catch (MailException e) {
            System.err.println("Failed to send email due to other errors: " + e.getMessage());
            return false;
        }
    }
}

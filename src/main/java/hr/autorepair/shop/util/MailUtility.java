package hr.autorepair.shop.util;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailUtility {

    private final AppProperties appProperties;

    public SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(appProperties.getProperty("spring.mail.username"));
        return simpleMailMessage;
    }

}

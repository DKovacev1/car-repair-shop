package hr.autorepair.shop.util;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@AllArgsConstructor
public class MailUtility {

    private final AppProperties appProperties;

    public SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(appProperties.getProperty("spring.mail.username"));
        return simpleMailMessage;
    }

    public boolean emailAddressExist(String emailAddress) {
        String url = "https://api.hunter.io/v2/email-verifier?email=" + emailAddress + "&api_key=" + appProperties.getProperty("hunter.io.api.key");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                String result = jsonResponse.getJSONObject("data").getString("result");

                // Provjera statusa "deliverable" ili "undeliverable"
                return "deliverable".equalsIgnoreCase(result);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error checking email: " + e.getMessage());
        }
        return false;
    }

}
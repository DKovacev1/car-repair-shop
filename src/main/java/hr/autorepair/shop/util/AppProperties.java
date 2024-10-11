package hr.autorepair.shop.util;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppProperties {

    private final Environment environment;

    public String getProperty(String key) {
        String property = environment.getProperty(key);
        if(property == null)
            throw new NullPointerException("Ne postoji property naziva " + key + ".");

        return property;
    }

}

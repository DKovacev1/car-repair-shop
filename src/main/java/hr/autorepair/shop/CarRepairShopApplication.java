package hr.autorepair.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
public class CarRepairShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRepairShopApplication.class, args);
	}

}

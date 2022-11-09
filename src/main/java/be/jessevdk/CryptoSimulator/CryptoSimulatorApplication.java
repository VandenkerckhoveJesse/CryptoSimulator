package be.jessevdk.CryptoSimulator;

import be.jessevdk.CryptoSimulator.config.RsaKeyProperties;
import be.jessevdk.CryptoSimulator.services.ApplicationUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class CryptoSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoSimulatorApplication.class, args);
	}


	/*
	@Bean
	CommandLineRunner runner(CurrencyRepository repository) {
		return args -> {
			Currency cur = new Currency("bitcoin", "Bitcoin", "BTC");
			Currency cur2 = new Currency("ethereum-classic", "Ethereum", "ETH");
			repository.insert(cur);
			repository.insert(cur2);
		};
	}*/


	@Bean
	CommandLineRunner runner(ApplicationUserService service) {
		return args -> {
			service.createNewUser("anya", "123");
		};
	}

}

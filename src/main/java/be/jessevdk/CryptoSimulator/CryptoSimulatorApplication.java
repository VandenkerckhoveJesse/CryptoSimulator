package be.jessevdk.CryptoSimulator;

import be.jessevdk.CryptoSimulator.auth.ApplicationUser;
import be.jessevdk.CryptoSimulator.config.RsaKeyProperties;
import be.jessevdk.CryptoSimulator.models.domain.Coin;
import be.jessevdk.CryptoSimulator.models.domain.Portfolio;
import be.jessevdk.CryptoSimulator.repositories.UserRepository;
import be.jessevdk.CryptoSimulator.services.ApplicationUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;

import java.util.List;

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
	CommandLineRunner runner(ApplicationUserDetailsService service) {
		return args -> {
			ApplicationUser user = new ApplicationUser("anya", "123", new Portfolio(List.of(new Coin("bitcoin", "BTC", 35))));
			service.saveUser(user);
		};
	}

}

package be.jessevdk.CryptoSimulator;

import be.jessevdk.CryptoSimulator.currency.Currency;
import be.jessevdk.CryptoSimulator.currency.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptoSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoSimulatorApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner(CurrencyRepository repository) {
		return args -> {
				Currency cur = new Currency("Ethereum", "ETH");
			repository.insert(cur);
		};
	}*/

}

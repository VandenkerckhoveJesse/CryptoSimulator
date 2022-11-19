package be.jessevdk.CryptoSimulator.config;

import be.jessevdk.CryptoSimulator.converters.BigDecimalDecimal128Converter;
import be.jessevdk.CryptoSimulator.converters.Decimal128BigDecimalConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.beans.BeanProperty;
import java.util.Arrays;

@Configuration
@EnableMongoRepositories(basePackages = "be.jessevdk.CryptoSimulator.repositories")
public class CryptoSimulatorConfig {

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl("https://api.coincap.io/v2/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new BigDecimalDecimal128Converter(),
                new Decimal128BigDecimalConverter()
        ));
    }
}
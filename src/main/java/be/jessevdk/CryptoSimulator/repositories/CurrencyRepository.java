package be.jessevdk.CryptoSimulator.repositories;

import be.jessevdk.CryptoSimulator.models.domain.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyRepository
        extends MongoRepository<Currency, String> {
}

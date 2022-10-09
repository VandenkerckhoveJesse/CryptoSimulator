package be.jessevdk.CryptoSimulator.currency;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyRepository
        extends MongoRepository<Currency, String> {
}

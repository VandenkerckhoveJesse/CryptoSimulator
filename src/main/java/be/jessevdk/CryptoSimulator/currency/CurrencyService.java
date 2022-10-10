package be.jessevdk.CryptoSimulator.currency;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CurrencyService {

    public List<Currency> getAllCurrencies() {
        return List.of(
                new Currency(
                        "chacha", "Bitcoin", "25C4"
                ),
                new Currency(
                        "krkao", "Ethereum", "88D9"
                )
        );
    }

    public Currency getCurrency(String id) {
        return new Currency(id, "Bitcoin", "25C4");
    }

}

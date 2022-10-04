package be.jessevdk.CryptoSimulator.currency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/currency")
public class CurrencyController {
    @GetMapping
    public List<Currency> getAllCurrencies() {
        return List.of(
                new Currency(
                        1L, "Bitcoin", "25C4"
                ),
                new Currency(
                        2L, "Ethereum", "88D9"
                )
        );
    }
    @GetMapping("{id}")
    public Currency getCurrency(@PathVariable Long id) {
        return new Currency(id, "Bitcoin", "25C4");
    }
}

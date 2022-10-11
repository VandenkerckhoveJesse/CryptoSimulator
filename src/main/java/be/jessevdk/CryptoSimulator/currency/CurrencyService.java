package be.jessevdk.CryptoSimulator.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) { this.currencyRepository = currencyRepository; }

    public List<Currency> getAllCurrencies() {
        List<Currency> currencies =  currencyRepository.findAll();
        currencies.stream().forEach(currency -> currency.setPrice(2.5));
        return currencies;
    }

    public Optional<Currency> getCurrency(String id) {
        var curr = currencyRepository.findById(id);
        if(curr.isPresent()) {
            curr.get().setPrice(2.0);
            return curr;
        }
        return currencyRepository.findById(id);
    }

}

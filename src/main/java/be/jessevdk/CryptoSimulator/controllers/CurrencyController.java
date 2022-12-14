package be.jessevdk.CryptoSimulator.controllers;

import be.jessevdk.CryptoSimulator.models.dto.CurrencyDTO;
import be.jessevdk.CryptoSimulator.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping
    public List<CurrencyDTO> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }
    @GetMapping("{id}")
    public CurrencyDTO getCurrency(@PathVariable String id) {
        return currencyService.getCurrency(id);
    }
}

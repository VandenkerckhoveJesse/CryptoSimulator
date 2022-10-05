package be.jessevdk.CryptoSimulator.portfolio;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import be.jessevdk.CryptoSimulator.currency.Currency;

import javax.sound.sampled.Port;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/portfolio")
public class PortfolioController {
    @GetMapping
    public Portfolio getPortfolio() {
        return new Portfolio(List.of(new Coin(1L, "Bitcoin", "BIT", 2.0)), 5);
    }

    @GetMapping("value")
    public double getValue() {
        return new Portfolio(List.of(new Coin(1L, "Bitcoin", "BIT", 2.0)), 5).getValue();
    }
}

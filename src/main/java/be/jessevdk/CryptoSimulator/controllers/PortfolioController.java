package be.jessevdk.CryptoSimulator.controllers;


import be.jessevdk.CryptoSimulator.models.domain.Coin;
import be.jessevdk.CryptoSimulator.models.domain.Portfolio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/portfolio")
public class PortfolioController {
    @GetMapping
    public Portfolio getPortfolio() {
        return new Portfolio(List.of(new Coin(1L, "Bitcoin", "BIT", 2.0)));
    }

    @GetMapping("value")
    public double getValue() {
        return 2;
    }
}

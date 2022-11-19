package be.jessevdk.CryptoSimulator.controllers;


import be.jessevdk.CryptoSimulator.models.dto.CoinDTO;
import be.jessevdk.CryptoSimulator.models.dto.PortfolioDTO;
import be.jessevdk.CryptoSimulator.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping(path = "api/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;
    @GetMapping
    public PortfolioDTO getPortfolio(Principal principal) {
        return portfolioService.getPortfolio(principal.getName());
    }

    @PostMapping(path="coins/buy")
    public void buyCoin(Principal principal, @RequestParam String id, @RequestParam BigDecimal amount) {
        portfolioService.buyCoin(principal.getName(), id, amount);
    }

    @PostMapping(path="coins/sell")
    public void sellCoin(Principal principal, @RequestParam String id, @RequestParam BigDecimal amount) {
        portfolioService.sellCoin(principal.getName(), id, amount);
    }
}

package be.jessevdk.CryptoSimulator.controllers;


import be.jessevdk.CryptoSimulator.models.dto.CoinDTO;
import be.jessevdk.CryptoSimulator.models.dto.PortfolioDTO;
import be.jessevdk.CryptoSimulator.models.request.BuyCoinRequest;
import be.jessevdk.CryptoSimulator.models.request.SellCoinRequest;
import be.jessevdk.CryptoSimulator.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;

    @GetMapping
    public PortfolioDTO getPortfolio(Principal principal) {
        return portfolioService.getPortfolio(principal.getName());
    }


    @PostMapping(path="coins/buy")
    public void buyCoin(Principal principal, @RequestBody BuyCoinRequest body) {
        portfolioService.buyCoin(principal.getName(), body.getId(), body.getAmount());
    }

    @PostMapping(path="coins/sell")
    public void sellCoin(Principal principal, @RequestBody SellCoinRequest body) {
        portfolioService.sellCoin(principal.getName(), body.getId(), body.getAmount());
    }
}

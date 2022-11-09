package be.jessevdk.CryptoSimulator.controllers;


import be.jessevdk.CryptoSimulator.models.dto.PortfolioDTO;
import be.jessevdk.CryptoSimulator.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "api/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;
    /*@GetMapping
    public PortfolioDTO getPortfolio(Principal principal) {
        return portfolioService.getPortfolio(principal.getName());
    }*/

    @GetMapping("value")
    public double getValue() {
        return 2;
    }
}

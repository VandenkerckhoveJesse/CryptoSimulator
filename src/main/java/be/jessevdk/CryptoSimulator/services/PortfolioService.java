package be.jessevdk.CryptoSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
    @Autowired
    private CurrencyService currencyService;

    
}

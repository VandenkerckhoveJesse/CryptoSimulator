package be.jessevdk.CryptoSimulator.services;

import be.jessevdk.CryptoSimulator.models.dto.PortfolioDTO;
import be.jessevdk.CryptoSimulator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private UserRepository userRepository;

    /*
    public PortfolioDTO getPortfolio(String username) {
        Portfolio portfolio = userRepository.findPortfolioByUsername(username);
        return new PortfolioDTO();
    }*/

}

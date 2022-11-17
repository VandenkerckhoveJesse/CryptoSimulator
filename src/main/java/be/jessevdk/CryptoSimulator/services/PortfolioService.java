package be.jessevdk.CryptoSimulator.services;

import be.jessevdk.CryptoSimulator.auth.ApplicationUser;
import be.jessevdk.CryptoSimulator.collectors.PortfolioValueAndCoinDTOToListCollector;
import be.jessevdk.CryptoSimulator.exceptions.InsufficientWalletFundsException;
import be.jessevdk.CryptoSimulator.exceptions.UserNotFoundException;
import be.jessevdk.CryptoSimulator.models.api.Asset;
import be.jessevdk.CryptoSimulator.models.api.GetAssetsResponse;
import be.jessevdk.CryptoSimulator.models.domain.Coin;
import be.jessevdk.CryptoSimulator.models.dto.CoinDTO;
import be.jessevdk.CryptoSimulator.models.dto.CurrencyDTO;
import be.jessevdk.CryptoSimulator.models.dto.PortfolioDTO;
import be.jessevdk.CryptoSimulator.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.stream.*;

@Service
public class PortfolioService {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebClient webClient;

    @Autowired
    private ModelMapper modelMapper;


    public PortfolioDTO getPortfolio(String username) {
        ApplicationUser user = userRepository.findByUsername(username);
        return mapPortfolioToPortfolioDTO(user.getPortfolio());
    }

    private PortfolioDTO mapPortfolioToPortfolioDTO(List<Coin> coins) {
        GetAssetsResponse apiResponse = webClient.get()
                .uri("/assets/")
                .retrieve()
                .bodyToMono(GetAssetsResponse.class)
                .block();

        Map<String, Double> assetPricesMap = apiResponse.getData().stream()
                .collect(Collectors.toMap(Asset::getId, Asset::getPriceUsd));

        Stream<CoinDTO> coinDTOSStream = coins
                .stream().map(coin -> mapCoinToCoinDTO(coin, assetPricesMap.get(coin.getId())));

        var results = coinDTOSStream.collect(PortfolioValueAndCoinDTOToListCollector.toPortfolioValueAndCoinDTOListCollectorResult());
        return new PortfolioDTO(results.getCoinDTOList(),
                results.getPortfolioValue());
    }



    private CoinDTO mapCoinToCoinDTO(Coin coin, double priceUsd) {
        CoinDTO dto = modelMapper.map(coin, be.jessevdk.CryptoSimulator.models.dto.CoinDTO.class);
        dto.setPriceUsd(priceUsd);
        dto.setValueUsd(priceUsd*coin.getAmount());
        return dto;
    }


    public void buyCoin(String username, String coinId, double amount) {
        CurrencyDTO currencyDTO = currencyService.getCurrency(coinId);
        var transactionCost = currencyDTO.getPriceUsd() * amount;
        var coin = new Coin(currencyDTO.getId(), currencyDTO.getName(), currencyDTO.getSymbol(), amount);
        try{
            userRepository.decreaseWalletUsd(username, transactionCost); //If throws exception no coins don't get pushed
            userRepository.pushCoinToPortfolio(username, coin);
        } catch (UserNotFoundException | InsufficientWalletFundsException e) {
            throw e;
        }
    }

    public void sellCoin(String username, String coinId, double amount) {
        /*CurrencyDTO currencyDTO = currencyService.getCurrency(coinId);
        var transactionValue = amount*currencyDTO.getPriceUsd();
        var coin = new Coin(currencyDTO.getId(), currencyDTO.getName(), currencyDTO.getSymbol(), amount);
        userRepository.removeCoinFromPortfolio(
                username, coin, transactionValue
        );*/
    }
}

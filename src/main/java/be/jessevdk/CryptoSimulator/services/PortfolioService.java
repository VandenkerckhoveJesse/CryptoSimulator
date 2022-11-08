package be.jessevdk.CryptoSimulator.services;

import be.jessevdk.CryptoSimulator.auth.ApplicationUser;
import be.jessevdk.CryptoSimulator.collectors.PortfolioValueAndCoinDTOToListCollector;
import be.jessevdk.CryptoSimulator.models.api.Asset;
import be.jessevdk.CryptoSimulator.models.api.GetAssetsResponse;
import be.jessevdk.CryptoSimulator.models.domain.Coin;
import be.jessevdk.CryptoSimulator.models.dto.CoinDTO;
import be.jessevdk.CryptoSimulator.models.dto.PortfolioDTO;
import be.jessevdk.CryptoSimulator.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
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
        PortfolioDTO portfolioDTO = mapPortfolioToPortfolioDTO(user.getPortfolio());

        return new PortfolioDTO();
        /*
        GetAssetResponse apiResponse = webClient.get()
                .uri("/assets/"+currency.getId())
                .retrieve()
                .bodyToMono(GetAssetResponse.class)
                .log()
                .block();//For now using blocking behaviour, can be updated in future
         */
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
                .stream().map(coin -> mapToCoinDTO(coin, assetPricesMap.get(coin.getId())));

        var results = coinDTOSStream.collect(PortfolioValueAndCoinDTOToListCollector.toPortfolioValueAndCoinDTOListCollectorResult());


        return null;
    }



    private CoinDTO mapToCoinDTO(Coin coin, double priceUsd) {
        be.jessevdk.CryptoSimulator.models.dto.CoinDTO dto = modelMapper.map(coin, be.jessevdk.CryptoSimulator.models.dto.CoinDTO.class);
        dto.setPriceUsd(priceUsd);
        dto.setValueUsd(priceUsd*coin.getAmount());
        return dto;
    }

}

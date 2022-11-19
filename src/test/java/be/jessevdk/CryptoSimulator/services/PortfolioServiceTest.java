package be.jessevdk.CryptoSimulator.services;

import be.jessevdk.CryptoSimulator.auth.ApplicationUser;
import be.jessevdk.CryptoSimulator.models.api.Asset;
import be.jessevdk.CryptoSimulator.models.api.GetAssetsResponse;
import be.jessevdk.CryptoSimulator.models.domain.Coin;
import be.jessevdk.CryptoSimulator.models.dto.PortfolioDTO;
import be.jessevdk.CryptoSimulator.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    @InjectMocks
    private PortfolioService portfolioService;

    @Mock
    private UserRepository userRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;


    @BeforeEach
    void beforeEach() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    }


    @Test
    void getPortfolioValueForOneCoin() {
        List<Coin> mockCoins = List.of(new Coin("bitcoin", "Bitcoin", "BTC", BigDecimal.valueOf(3)));
        ApplicationUser mockUser = new ApplicationUser();
        mockUser.setPortfolio(mockCoins);
        GetAssetsResponse mockResponse = new GetAssetsResponse();
        List<Asset> mockAssets = List.of(
                new Asset("bitcoin", 1L, "BTC", BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(30), BigDecimal.valueOf(3), BigDecimal.valueOf(3), "explore"),
                new Asset("ethereum-classic", 2L, "eth", BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(30), BigDecimal.valueOf(3), BigDecimal.valueOf(3), "explore"));
        mockResponse.setData(mockAssets);
        mockResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
        when(userRepository.findByUsername("jesse")).thenReturn(mockUser);
        when(responseSpec.bodyToMono(GetAssetsResponse.class)).thenReturn(Mono.just(mockResponse));

        var result = portfolioService.getPortfolio("jesse");

        BigDecimal expected = calculatePortfolioValue(mockCoins, mockAssets);
        assertEquals(expected, result.getValueUsd());
    }

    @Test
    void getPortfolioValueForMultipleCoins() {
        List<Coin> mockCoins = List.of(
                new Coin("bitcoin", "Bitcoin", "BTC", BigDecimal.valueOf(3)),
                new Coin("ethereum", "Ethereum", "ETH", BigDecimal.valueOf(6.99)),
                new Coin("solaria", "Solaria", "SOL", BigDecimal.valueOf(6.776)));
        ApplicationUser mockUser = new ApplicationUser();
        mockUser.setPortfolio(mockCoins);
        GetAssetsResponse mockResponse = new GetAssetsResponse();
        List<Asset> mockAssets = List.of(
                new Asset("bitcoin", 1L, "BTC", BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(30), BigDecimal.valueOf(3), BigDecimal.valueOf(3), "explore"),
                new Asset("ethereum", 2L, "ETH", BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), "explore"),
                new Asset("solaria", 3L, "SOL", BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), "explore"));
        mockResponse.setData(mockAssets);
        mockResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
        when(userRepository.findByUsername("jesse")).thenReturn(mockUser);
        when(responseSpec.bodyToMono(GetAssetsResponse.class)).thenReturn(Mono.just(mockResponse));

        PortfolioDTO result = portfolioService.getPortfolio("jesse");

        BigDecimal expected = calculatePortfolioValue(mockCoins, mockAssets);
        assertEquals(expected, result.getValueUsd());
    }

    @Test
    void getPortfolioValueForZeroCoins() {
        List<Coin> mockCoins = List.of();
        ApplicationUser mockUser = new ApplicationUser();
        mockUser.setPortfolio(mockCoins);
        GetAssetsResponse mockResponse = new GetAssetsResponse();
        List<Asset> mockAssets = List.of(
                new Asset("bitcoin", 1L, "BTC", BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(30), BigDecimal.valueOf(3), BigDecimal.valueOf(3), "explore"),
                new Asset("ethereum", 2L, "ETH", BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(398.09), BigDecimal.valueOf(3), BigDecimal.valueOf(3), "explore"),
                new Asset("solaria", 3L, "SOL", BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(99.8), BigDecimal.valueOf(3), BigDecimal.valueOf(3), "explore"));
        mockResponse.setData(mockAssets);
        mockResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
        when(userRepository.findByUsername("jesse")).thenReturn(mockUser);
        when(responseSpec.bodyToMono(GetAssetsResponse.class)).thenReturn(Mono.just(mockResponse));

        PortfolioDTO result = portfolioService.getPortfolio("jesse");

        BigDecimal expected = calculatePortfolioValue(mockCoins, mockAssets);
        assertEquals(expected, result.getValueUsd());
    }

    private BigDecimal calculatePortfolioValue(List<Coin> coins, List<Asset> assets) {
        Map<String, BigDecimal> values = assets.stream()
                .collect(Collectors.toMap(Asset::getId, Asset::getPriceUsd));
        var calculatedValuesStream = coins.stream()
                .map((coin) -> coin.getAmount().multiply(values.get(coin.getId())));
        return calculatedValuesStream.reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
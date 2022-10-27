package be.jessevdk.CryptoSimulator.services;

import be.jessevdk.CryptoSimulator.models.api.Asset;
import be.jessevdk.CryptoSimulator.models.api.GetAssetResponse;
import be.jessevdk.CryptoSimulator.models.api.GetAssetsResponse;
import be.jessevdk.CryptoSimulator.models.domain.Currency;
import be.jessevdk.CryptoSimulator.models.dto.CurrencyDTO;
import be.jessevdk.CryptoSimulator.repositories.CurrencyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {
    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    @Spy
    ModelMapper modelMapper = new ModelMapper();

    //Al mocks regarding webclient
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
    void getAllCurrencies() {
        List<Currency> mockCurrencies = List.of(new Currency("bitcoin", "Bitcoin", "BTC"), new Currency("ethereum-classic", "Ethereum", "ETH"));
        GetAssetsResponse mockResponse = new GetAssetsResponse();
        List<Asset> mockAssets = List.of(
                new Asset("bitcoin", 1L, "BTC", 2, 50, 2500, 85500, 30, -20, 55, "explore"),
                new Asset("ethereum-classic", 2L, "eth", 2, 50, 1200, 200, 30, 20, 55, "explore"));
        mockResponse.setData(mockAssets);
        mockResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
        when(currencyRepository.findAll()).thenReturn(mockCurrencies);
        when(responseSpec.bodyToMono(GetAssetsResponse.class)).thenReturn(Mono.just(mockResponse));

        List<CurrencyDTO> result = currencyService.getAllCurrencies();

        List<CurrencyDTO> expected = mockCurrencies
                .stream()
                .map(currency -> {
                    var res = modelMapper.map(currency, CurrencyDTO.class);
                    res.setPriceUsd(30);
                    return res;
                })
                .collect(Collectors.toList());
        assertEquals(expected, result);
    }

    @Test
    void getCurrency() {
        Currency mockCurrency = new Currency("bitcoin", "Bitcoin", "BTC");
        GetAssetResponse mockResponse = new GetAssetResponse();
        Asset mockAsset = new Asset("bitcoin", 1L, "BTC", 2, 50, 2500, 85500, 88, -20, 55, "explore");
        mockResponse.setData(mockAsset);
        mockResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
        when(currencyRepository.findById("bitcoin")).thenReturn(java.util.Optional.of(mockCurrency));
        when(responseSpec.bodyToMono(GetAssetResponse.class)).thenReturn(Mono.just(mockResponse));

        CurrencyDTO result = currencyService.getCurrency("bitcoin");

        CurrencyDTO expected = modelMapper.map(mockCurrency, CurrencyDTO.class);
        expected.setPriceUsd(mockAsset.getPriceUsd());
        assertEquals(expected, result);
    }
}
package be.jessevdk.CryptoSimulator.services;

import be.jessevdk.CryptoSimulator.models.api.Asset;
import be.jessevdk.CryptoSimulator.models.api.GetAssetResponse;
import be.jessevdk.CryptoSimulator.models.api.GetAssetsResponse;
import be.jessevdk.CryptoSimulator.models.domain.Currency;
import be.jessevdk.CryptoSimulator.models.dto.CurrencyDTO;
import be.jessevdk.CryptoSimulator.repositories.CurrencyRepository;
import be.jessevdk.CryptoSimulator.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final WebClient webClient;
    private final ModelMapper modelMapper;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, WebClient webClient, ModelMapper modelMapper) {
        this.currencyRepository = currencyRepository;
        this.webClient = webClient;
        this.modelMapper = modelMapper;
    }

    public List<CurrencyDTO> getAllCurrencies() {
        List<Currency> currencies =  currencyRepository.findAll();
        GetAssetsResponse apiResponse = webClient.get()
                .uri("/assets")
                .retrieve()
                .bodyToMono(GetAssetsResponse.class)
                .block();//For now using blocking behaviour, can be updated in future
        Map<String, Asset> assetMap = apiResponse.getData().stream()
                .collect(Collectors.toMap(Asset::getId, Function.identity()));
        List<CurrencyDTO> currencyDTOS = currencies.stream()
                .flatMap(currency -> Optional.ofNullable(assetMap.get(currency.getId()))
                        .map(asset -> Stream.of(new CurrencyDTO(currency, asset))).orElse(null))
                .collect(Collectors.toList());
        return currencyDTOS;
    }

    public CurrencyDTO getCurrency(String id) {
        Currency currency = currencyRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        CurrencyDTO currencyDTO = modelMapper.map(currency, CurrencyDTO.class);
        GetAssetResponse apiResponse = webClient.get()
                .uri("/assets/"+currency.getId())
                .retrieve()
                .bodyToMono(GetAssetResponse.class)
                .log()
                .block();//For now using blocking behaviour, can be updated in future
        currencyDTO.setPriceUsd(apiResponse.getData().getPriceUsd());
        return currencyDTO;
    }

}

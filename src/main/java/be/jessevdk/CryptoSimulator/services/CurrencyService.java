package be.jessevdk.CryptoSimulator.services;

import be.jessevdk.CryptoSimulator.models.domain.Currency;
import be.jessevdk.CryptoSimulator.models.dto.CurrencyDTO;
import be.jessevdk.CryptoSimulator.repositories.CurrencyRepository;
import be.jessevdk.CryptoSimulator.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

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
        List<CurrencyDTO> currencyDTOS = currencies
                .stream()
                .map(currency -> modelMapper.map(currency, CurrencyDTO.class))
                .collect(Collectors.toList());
        return currencyDTOS;
    }

    public CurrencyDTO getCurrency(String id) {
        Currency currency = currencyRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        CurrencyDTO currencyDTO = modelMapper.map(currency, CurrencyDTO.class);
        return currencyDTO;
    }

}

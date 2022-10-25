package be.jessevdk.CryptoSimulator.currency;

import be.jessevdk.CryptoSimulator.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
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

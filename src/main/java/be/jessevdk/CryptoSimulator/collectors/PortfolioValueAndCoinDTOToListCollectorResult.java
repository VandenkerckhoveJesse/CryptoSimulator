package be.jessevdk.CryptoSimulator.collectors;

import be.jessevdk.CryptoSimulator.models.dto.CoinDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PortfolioValueAndCoinDTOToListCollectorResult {
    private BigDecimal portfolioValue;
    private List<CoinDTO> coinDTOList;

    public PortfolioValueAndCoinDTOToListCollectorResult() {
        this.coinDTOList = new ArrayList<CoinDTO>(); //todo check if this is the best implementation of list
        this.portfolioValue = BigDecimal.ZERO;
    }

    public BigDecimal getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(BigDecimal portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public List<CoinDTO> getCoinDTOList() {
        return coinDTOList;
    }

    public void setCoinDTOList(List<CoinDTO> coinDTOList) {
        this.coinDTOList = coinDTOList;
    }

    public void addToPortfolioValue(BigDecimal amount) {
        this.portfolioValue = this.portfolioValue.add(amount);
    }

    public void addToCoinDTOList(CoinDTO dto) {
        this.coinDTOList.add(dto);
    }
}

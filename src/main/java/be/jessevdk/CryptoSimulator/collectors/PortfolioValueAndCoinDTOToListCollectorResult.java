package be.jessevdk.CryptoSimulator.collectors;

import be.jessevdk.CryptoSimulator.models.domain.Coin;
import be.jessevdk.CryptoSimulator.models.dto.CoinDTO;

import java.util.ArrayList;
import java.util.List;

public class PortfolioValueAndCoinDTOToListCollectorResult {
    private double portfolioValue;
    private List<CoinDTO> coinDTOList;

    public PortfolioValueAndCoinDTOToListCollectorResult() {
        this.coinDTOList = new ArrayList<CoinDTO>(); //todo check if this is the best implementation of list
    }

    public double getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(double portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public List<CoinDTO> getCoinDTOList() {
        return coinDTOList;
    }

    public void setCoinDTOList(List<CoinDTO> coinDTOList) {
        this.coinDTOList = coinDTOList;
    }

    public void addToPortfolioValue(double amount) {
        this.portfolioValue += amount;
    }

    public void addToCoinDTOList(CoinDTO dto) {
        this.coinDTOList.add(dto);
    }
}

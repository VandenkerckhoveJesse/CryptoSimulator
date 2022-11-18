package be.jessevdk.CryptoSimulator.models.dto;

import be.jessevdk.CryptoSimulator.models.domain.Coin;

import java.util.List;

public class PortfolioDTO {
    private List<CoinDTO> coins;
    private double valueUsd; //todo Floating-Point Arithmetic, double should not be used for financial precise calculations

    public PortfolioDTO() {
    }

    public PortfolioDTO(List<CoinDTO> coins, double valueUsd) {
        this.coins = coins;
        this.valueUsd = valueUsd;
    }

    public List<CoinDTO> getCoins() {
        return coins;
    }

    public void setCoins(List<CoinDTO> coins) {
        this.coins = coins;
    }

    public double getValueUsd() {
        return valueUsd;
    }

    public void setValueUsd(double valueUsd) {
        this.valueUsd = valueUsd;
    }
}

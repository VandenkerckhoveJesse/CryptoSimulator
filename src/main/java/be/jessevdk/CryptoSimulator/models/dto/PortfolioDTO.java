package be.jessevdk.CryptoSimulator.models.dto;

import be.jessevdk.CryptoSimulator.models.domain.Coin;

import java.util.List;

public class PortfolioDTO {
    private List<Coin> coins;
    private double value;

    public PortfolioDTO() {
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

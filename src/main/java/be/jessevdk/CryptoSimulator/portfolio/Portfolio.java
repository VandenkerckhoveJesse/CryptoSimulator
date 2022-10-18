package be.jessevdk.CryptoSimulator.portfolio;

import be.jessevdk.CryptoSimulator.currency.Currency;
import org.springframework.data.annotation.Transient;

import java.util.HashMap;
import java.util.List;

public class Portfolio {
    List<Coin> coins;
    //todo: add left money
    @Transient
    private double value;

    public Portfolio(List<Coin> coins, double value) {
        this.coins = coins;
        this.value = value;
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


package be.jessevdk.CryptoSimulator.models.domain;

import be.jessevdk.CryptoSimulator.models.domain.Coin;

import java.util.List;

public class Portfolio {
    List<Coin> coins;

    public Portfolio(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }


}


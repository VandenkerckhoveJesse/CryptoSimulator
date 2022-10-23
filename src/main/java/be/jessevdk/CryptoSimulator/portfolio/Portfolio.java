package be.jessevdk.CryptoSimulator.portfolio;

import be.jessevdk.CryptoSimulator.currency.Currency;
import org.springframework.data.annotation.Transient;

import java.util.HashMap;
import java.util.List;

public class Portfolio {
    List<Coin> coins;
    //todo: add left money

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


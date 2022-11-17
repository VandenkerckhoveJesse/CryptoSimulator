package be.jessevdk.CryptoSimulator.repositories;

import be.jessevdk.CryptoSimulator.models.domain.Coin;

public interface CustomUserRepository {
    public void decreaseWalletUsd(String username, double amountUsd);

    public void pushCoinToPortfolio(String username, Coin coin);


    /*public void addCoinToPortfolio(String username, Coin coin, double priceUsd);

    public void removeCoinFromPortfolio(String username, Coin coin, double transactionValue);*/
}

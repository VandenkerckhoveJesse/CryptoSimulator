package be.jessevdk.CryptoSimulator.repositories;

import be.jessevdk.CryptoSimulator.exceptions.InsufficientWalletFundsException;
import be.jessevdk.CryptoSimulator.exceptions.UserNotFoundException;
import be.jessevdk.CryptoSimulator.models.domain.Coin;

public interface CustomUserRepository {
    public void decreaseWalletUsd(String username, double amountUsd) throws UserNotFoundException, InsufficientWalletFundsException;

    public void addCoinToPortfolio(String username, Coin coin);

    public void removeCoinFromPortfolio(String username, Coin coin);

    public double getCoinAmount(String username, String coinId);

    /*public void addCoinToPortfolio(String username, Coin coin, double priceUsd);

    public void removeCoinFromPortfolio(String username, Coin coin, double transactionValue);*/
}

package be.jessevdk.CryptoSimulator.repositories;

import be.jessevdk.CryptoSimulator.models.domain.Coin;

public interface CustomUserRepository {
    public void pushCoinsToPortfolioAndDecreaseWallet(String username, Coin coin, double priceUsd);
}

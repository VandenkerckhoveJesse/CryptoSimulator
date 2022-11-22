package be.jessevdk.CryptoSimulator.models.dto;

import be.jessevdk.CryptoSimulator.models.domain.Coin;

import java.math.BigDecimal;
import java.util.List;

public class PortfolioDTO {
    private List<CoinDTO> coins;
    private BigDecimal valueUsd;

    private BigDecimal walletUsd;

    public PortfolioDTO() {
    }

    public PortfolioDTO(List<CoinDTO> coins, BigDecimal valueUsd, BigDecimal walletUsd) {
        this.coins = coins;
        this.valueUsd = valueUsd;
        this.walletUsd = walletUsd;
    }

    public List<CoinDTO> getCoins() {
        return coins;
    }

    public void setCoins(List<CoinDTO> coins) {
        this.coins = coins;
    }

    public BigDecimal getValueUsd() {
        return valueUsd;
    }

    public void setValueUsd(BigDecimal valueUsd) {
        this.valueUsd = valueUsd;
    }

    public BigDecimal getWalletUsd() {
        return walletUsd;
    }

    public void setWalletUsd(BigDecimal walletUsd) {
        this.walletUsd = walletUsd;
    }
}

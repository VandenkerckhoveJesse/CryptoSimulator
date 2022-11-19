package be.jessevdk.CryptoSimulator.models.dto;

import java.math.BigDecimal;

public class CoinDTO {
    private String id;
    private String name;
    private String symbol;
    private BigDecimal amount;

    private BigDecimal priceUsd; //todo Floating-Point Arithmetic, double should not be used for financial precise calculations

    private BigDecimal valueUsd;

    public CoinDTO() {
    }

    public CoinDTO(String id, String name, String symbol, BigDecimal amount, BigDecimal priceUsd, BigDecimal valueUsd) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.amount = amount;
        this.priceUsd = priceUsd;
        this.valueUsd = valueUsd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(BigDecimal priceUsd) {
        this.priceUsd = priceUsd;
    }

    public BigDecimal getValueUsd() {
        return valueUsd;
    }

    public void setValueUsd(BigDecimal valueUsd) {
        this.valueUsd = valueUsd;
    }
}

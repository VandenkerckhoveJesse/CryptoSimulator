package be.jessevdk.CryptoSimulator.models.dto;

public class CoinDTO {
    private String id;
    private String name;
    private String symbol;
    private double amount;

    private double priceUsd; //todo Floating-Point Arithmetic, double should not be used for financial precise calculations

    private double valueUsd;

    public CoinDTO() {
    }

    public CoinDTO(String id, String name, String symbol, double amount, double priceUsd, double valueUsd) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public double getValueUsd() {
        return valueUsd;
    }

    public void setValueUsd(double valueUsd) {
        this.valueUsd = valueUsd;
    }
}

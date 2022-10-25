package be.jessevdk.CryptoSimulator.models.domain;

import java.math.BigDecimal;

public class Coin {
    private Long id;
    private String name;
    private String symbol;
    private double amount;

    public Coin(Long id, String name, String symbol, double amount) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.amount = amount;
    }

    public Coin(String name, String symbol, double amount) {
        this.name = name;
        this.symbol = symbol;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

package be.jessevdk.CryptoSimulator.models.domain;

public class Coin {
    private String id;
    private String name;
    private String symbol;
    private double amount;

    public Coin(String id, String name, String symbol, double amount) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.amount = amount;
    }

    public Coin() {
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
}

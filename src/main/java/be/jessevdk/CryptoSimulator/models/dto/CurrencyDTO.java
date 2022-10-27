package be.jessevdk.CryptoSimulator.models.dto;

import be.jessevdk.CryptoSimulator.models.api.Asset;
import be.jessevdk.CryptoSimulator.models.domain.Currency;

import java.util.Objects;

public class CurrencyDTO {
    private String id;
    private String name;
    private String symbol;
    private double priceUsd;

    public CurrencyDTO() {
    }

    public CurrencyDTO(String id, String name, String symbol, double priceUsd) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
    }

    public CurrencyDTO(Currency currency, Asset asset) {
        this.id = currency.getId();
        this.name = currency.getName();
        this.symbol = currency.getSymbol();
        this.priceUsd = asset.getPriceUsd();
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

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDTO that = (CurrencyDTO) o;
        return Double.compare(that.priceUsd, priceUsd) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol, priceUsd);
    }
}

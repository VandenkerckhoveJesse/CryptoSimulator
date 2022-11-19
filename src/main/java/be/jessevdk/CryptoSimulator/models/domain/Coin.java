package be.jessevdk.CryptoSimulator.models.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128;

public class Coin {
    private String id;
    private String name;
    private String symbol;
    @Field(targetType = DECIMAL128)
    private BigDecimal amount;

    public Coin(String id, String name, String symbol, BigDecimal amount) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

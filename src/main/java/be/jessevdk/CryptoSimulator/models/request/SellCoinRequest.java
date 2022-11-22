package be.jessevdk.CryptoSimulator.models.request;

import java.math.BigDecimal;

public class SellCoinRequest {
    private String id;
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

package be.jessevdk.CryptoSimulator.models.request;

import java.math.BigDecimal;

public class BuyCoinRequest {
    private String id;
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

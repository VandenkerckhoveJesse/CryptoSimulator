package be.jessevdk.CryptoSimulator.models.api;

import java.sql.Timestamp;

public abstract class ApiResponse<T> {
    protected T data;
    protected Timestamp timestamp;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}


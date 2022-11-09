package be.jessevdk.CryptoSimulator.models.dto;

public class ApplicationUserDTO {
    private String id;
    private String username;

    public ApplicationUserDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

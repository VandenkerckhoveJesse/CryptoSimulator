package be.jessevdk.CryptoSimulator.auth;

import be.jessevdk.CryptoSimulator.models.domain.Coin;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document
public class ApplicationUser implements UserDetails {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;

    private double walletUsd;
    private List<Coin> portfolio;

    public ApplicationUser(String id, String username, String password, double walletUsd, List<Coin> portfolio) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.walletUsd = walletUsd;
        this.portfolio = portfolio;
    }

    public ApplicationUser(String username, String password, double walletUsd, List<Coin> portfolio) {
        this.username = username;
        this.password = password;
        this.walletUsd = walletUsd;
        this.portfolio = portfolio;
    }

    public ApplicationUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    public List<Coin> getPortfolio() {
        return portfolio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getWalletUsd() {
        return walletUsd;
    }

    public void setWalletUsd(double walletUsd) {
        this.walletUsd = walletUsd;
    }

    public void setPortfolio(List<Coin> portfolio) {
        this.portfolio = portfolio;
    }
}

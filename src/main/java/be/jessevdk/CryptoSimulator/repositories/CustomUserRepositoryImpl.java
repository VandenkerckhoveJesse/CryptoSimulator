package be.jessevdk.CryptoSimulator.repositories;

import be.jessevdk.CryptoSimulator.auth.ApplicationUser;
import be.jessevdk.CryptoSimulator.exceptions.CoinNotFoundException;
import be.jessevdk.CryptoSimulator.exceptions.InsufficientWalletFundsException;
import be.jessevdk.CryptoSimulator.exceptions.UserNotFoundException;
import be.jessevdk.CryptoSimulator.models.domain.Coin;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.*;

public class CustomUserRepositoryImpl implements CustomUserRepository {
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public void pushCoinsToPortfolioAndDecreaseWallet(String username, Coin coin, double priceUsd) {
        try{
            decreaseWalletUsd(username, priceUsd); //If decrease wallets throws exception, coins will not be added to portfolio
            pushCoinToPortfolio(username, coin);
        } catch (UserNotFoundException | InsufficientWalletFundsException exception) {
            throw exception;
        }
    }

    private void decreaseWalletUsd(String username, double priceUsd) throws UserNotFoundException, InsufficientWalletFundsException {
        var updateWallet = AggregationUpdate.update();
        updateWallet.set("walletUsd").toValue(
                ConditionalOperators
                        .when(ComparisonOperators.Gte.valueOf("walletUsd").greaterThanEqualToValue(priceUsd))
                        .thenValueOf(ArithmeticOperators.valueOf("walletUsd").subtract(priceUsd))
                        .otherwiseValueOf("walletUsd"));
        UpdateResult updateWalletResult = mongoTemplate.updateFirst(Query.query(Criteria.where("username").is(username)), updateWallet, ApplicationUser.class);
        if(updateWalletResult.getMatchedCount() == 0) throw new UserNotFoundException();
        if(updateWalletResult.getModifiedCount() == 0) throw new InsufficientWalletFundsException();
    }

    private void pushCoinToPortfolio(String username, Coin coin) {
        try {
            incrementCoin(username, coin);
        } catch (CoinNotFoundException exception) {
            addCoinToPortfolio(username, coin);
        }
    }

    private void incrementCoin(String username, Coin coin) throws CoinNotFoundException {
        var incrementAmountUpdate = new Update();
        incrementAmountUpdate.inc("portfolio.$.amount", coin.getAmount());
        var incrementResult = mongoTemplate.updateFirst(
                Query.query(Criteria.where("username").is(username).and("portfolio.id").is(coin.getId())),
                incrementAmountUpdate,
                ApplicationUser.class
        );
        if(incrementResult.getMatchedCount() == 0) throw new CoinNotFoundException();
    }

    private void addCoinToPortfolio(String username, Coin coin) {
        var update = new Update();
        var res = mongoTemplate.updateFirst(
                Query.query(Criteria.where("username").is(username)),
                update.addToSet("portfolio", coin),
                ApplicationUser.class
        );
    }
}

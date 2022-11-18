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
    public void decreaseWalletUsd(String username, double priceUsd) throws UserNotFoundException, InsufficientWalletFundsException {
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

    @Override
    public void addCoinToPortfolio(String username, Coin coin) {
        try {
            incrementCoin(username, coin);
        } catch (CoinNotFoundException exception) {
            pushCoinToPortfolioList(username, coin);
        }
    }

    @Override
    public void removeCoinFromPortfolio(String username, Coin coin) {
        decrementCoin(username, coin);
        var balk = "Df";
        //In future remove coins that hit amount 0.
    }

    @Override
    public double getCoinAmount(String username, String coinId) throws CoinNotFoundException {
        var query = new Query();
        query.fields().include("portfolio.$");
        query.addCriteria(Criteria.where("username").is(username).and("portfolio.id").is(coinId));
        var res = mongoTemplate.findOne(query, ApplicationUser.class);
        if(null == res) {
            throw new CoinNotFoundException();
        }
        return res.getPortfolio().get(0).getAmount();
    }

    private void decrementCoin(String username, Coin coin) {
        var decrementUpdate = AggregationUpdate.update();
        decrementUpdate.set("$.amount").toValue(90000);
        /*decrementUpdate.set("portfolio.$.amount").toValue(
                ConditionalOperators
                        .when(ComparisonOperators.Gte.valueOf("portfolio.$.amount").greaterThanEqualToValue(coin.getAmount()))
                        .thenValueOf(ArithmeticOperators.valueOf("portfolio.$.amount").subtract(coin.getAmount()))
                        .otherwiseValueOf("portfolio.$.amount"));*/
        UpdateResult updateResult = mongoTemplate.updateFirst(
                Query.query(Criteria.where("username").is(username).and("portfolio.id").is(coin.getId())),
                decrementUpdate,
                ApplicationUser.class);
        var df = 1;
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

    private void pushCoinToPortfolioList(String username, Coin coin) {
        var update = new Update();
        var res = mongoTemplate.updateFirst(
                Query.query(Criteria.where("username").is(username)),
                update.addToSet("portfolio", coin),
                ApplicationUser.class
        );
    }
}

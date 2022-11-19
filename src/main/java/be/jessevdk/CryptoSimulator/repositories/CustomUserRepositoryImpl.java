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

import java.math.BigDecimal;

public class CustomUserRepositoryImpl implements CustomUserRepository {
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public void decreaseWalletUsd(String username, BigDecimal priceUsd) throws UserNotFoundException, InsufficientWalletFundsException {
        var updateWallet = AggregationUpdate.update();
        updateWallet.set("walletUsd").toValue(
                ConditionalOperators
                        .when(ComparisonOperators.Gte.valueOf("walletUsd").greaterThanEqualToValue(priceUsd)).thenValueOf(ArithmeticOperators.valueOf("walletUsd").subtract(priceUsd))
                        .otherwiseValueOf("walletUsd"));
        UpdateResult updateWalletResult = mongoTemplate.updateFirst(Query.query(Criteria.where("username").is(username)), updateWallet, ApplicationUser.class);
        if(updateWalletResult.getMatchedCount() == 0) throw new UserNotFoundException();
        if(updateWalletResult.getModifiedCount() == 0) throw new InsufficientWalletFundsException();
    }

    @Override
    public void increaseWalletUsd(String username, BigDecimal amountUsd) {
        var update = new Update();
        update.inc("walletUsd", amountUsd);
        var query = new Query().addCriteria(Criteria.where("username").is(username));
        mongoTemplate.updateFirst(query, update, ApplicationUser.class);
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
        var update = new Update();
        update.pull("portfolio", coin);
        var query = new Query().addCriteria(Criteria.where("username").is(username).and("portfolio.id").is(coin.getId()));
        mongoTemplate.updateFirst(query, update, ApplicationUser.class);
    }

    @Override
    public void decreaseCoinAmount(String username, Coin coin) {
        var update = new Update();
        update.inc("portfolio.$.amount", coin.getAmount().negate());
        var query = new Query().addCriteria(Criteria.where("username").is(username).and("portfolio.id").is(coin.getId()));
        mongoTemplate.updateFirst(query, update, ApplicationUser.class);
    }

    @Override
    public BigDecimal getCoinAmount(String username, String coinId) throws CoinNotFoundException {
        var query = new Query();
        query.fields().include("portfolio.$");
        query.addCriteria(Criteria.where("username").is(username).and("portfolio.id").is(coinId));
        var res = mongoTemplate.findOne(query, ApplicationUser.class);
        if(null == res) {
            throw new CoinNotFoundException();
        }
        return res.getPortfolio().get(0).getAmount();
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

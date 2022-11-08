package be.jessevdk.CryptoSimulator.collectors;

import be.jessevdk.CryptoSimulator.models.dto.CoinDTO;
import be.jessevdk.CryptoSimulator.models.dto.PortfolioDTO;
import reactor.util.function.Tuple2;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PortfolioValueAndCoinDTOToListCollector
            implements Collector<CoinDTO, PortfolioValueAndCoinDTOToListCollectorResult, PortfolioValueAndCoinDTOToListCollectorResult> {

    public static PortfolioValueAndCoinDTOToListCollector toPortfolioValueAndCoinDTOListCollectorResult() {
        return new PortfolioValueAndCoinDTOToListCollector();
    }

    @Override
    public Supplier<PortfolioValueAndCoinDTOToListCollectorResult> supplier() {
        return PortfolioValueAndCoinDTOToListCollectorResult::new;
    }

    @Override
    public BiConsumer<PortfolioValueAndCoinDTOToListCollectorResult, CoinDTO> accumulator() {
        return (result, dto) -> {
            result.addToPortfolioValue(dto.getValueUsd());
            result.addToCoinDTOList(dto);
        };
    }

    @Override
    public BinaryOperator<PortfolioValueAndCoinDTOToListCollectorResult> combiner() {
        return (result1, result2) -> {
            result1.addToPortfolioValue(result2.getPortfolioValue());
            result1.getCoinDTOList().addAll(result2.getCoinDTOList());
            return result1;
        };
    }

    @Override
    public Function<PortfolioValueAndCoinDTOToListCollectorResult, PortfolioValueAndCoinDTOToListCollectorResult> finisher() {
        return (result) -> {
            return result;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}


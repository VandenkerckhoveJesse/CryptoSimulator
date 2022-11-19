package be.jessevdk.CryptoSimulator.converters;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.lang.NonNull;
import org.bson.types.Decimal128;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigDecimal;
@ReadingConverter
public class Decimal128BigDecimalConverter implements Converter<Decimal128, BigDecimal> {

    @Override
    public BigDecimal convert(Decimal128 source) {
        return source.bigDecimalValue();
    }
}

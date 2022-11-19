package be.jessevdk.CryptoSimulator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Insufficient coin amount or coin does not exist")
public class CoinNotFoundException extends RuntimeException{
}

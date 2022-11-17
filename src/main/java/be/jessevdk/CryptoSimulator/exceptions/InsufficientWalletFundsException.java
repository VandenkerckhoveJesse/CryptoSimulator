package be.jessevdk.CryptoSimulator.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Insufficient wallet funds")
public class InsufficientWalletFundsException extends RuntimeException {
}

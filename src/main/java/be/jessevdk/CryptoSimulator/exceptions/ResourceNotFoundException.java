package be.jessevdk.CryptoSimulator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested resource doesnt exist")
public class ResourceNotFoundException extends RuntimeException{
}

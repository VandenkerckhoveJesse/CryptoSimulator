package be.jessevdk.CryptoSimulator.controllers;

import be.jessevdk.CryptoSimulator.models.dto.ApplicationUserDTO;
import be.jessevdk.CryptoSimulator.models.request.CreateNewUserRequest;
import be.jessevdk.CryptoSimulator.services.ApplicationUserService;
import be.jessevdk.CryptoSimulator.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthorizationController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationController.class);
    private final TokenService tokenService;
    private final ApplicationUserService applicationUserService;

    public AuthorizationController(TokenService tokenService, ApplicationUserService applicationUserService) {
        this.tokenService = tokenService;
        this.applicationUserService = applicationUserService;
    }

    @CrossOrigin(origins = "http://localhost:8084")
    @PostMapping("token")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted {}", token);
        return token;
    }

    @CrossOrigin(origins = "http://localhost:8084")
    @PostMapping("signup")
    public ApplicationUserDTO createNewUser(@RequestBody CreateNewUserRequest body) {
        return applicationUserService.createNewUser(body.getUsername(), body.getPassword());
    }
}


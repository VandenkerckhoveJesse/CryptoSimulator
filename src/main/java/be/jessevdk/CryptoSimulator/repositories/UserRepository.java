package be.jessevdk.CryptoSimulator.repositories;

import be.jessevdk.CryptoSimulator.auth.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<ApplicationUser, String> {
    ApplicationUser findByUsername(String username);
}

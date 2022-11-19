package be.jessevdk.CryptoSimulator.repositories;

import be.jessevdk.CryptoSimulator.auth.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends
        MongoRepository<ApplicationUser, String>
        ,CustomUserRepository {
    ApplicationUser findByUsername(String username);
}

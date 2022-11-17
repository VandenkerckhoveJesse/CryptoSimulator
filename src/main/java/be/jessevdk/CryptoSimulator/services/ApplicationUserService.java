package be.jessevdk.CryptoSimulator.services;

import be.jessevdk.CryptoSimulator.auth.ApplicationUser;
import be.jessevdk.CryptoSimulator.models.dto.ApplicationUserDTO;
import be.jessevdk.CryptoSimulator.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if(user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public ApplicationUserDTO createNewUser(String username, String password) {
        var encodedPassword = passwordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(
                username,
                encodedPassword,
                10000, //todo put this in config so it can be changed
                List.of()
        );
        ApplicationUser createdUser = userRepository.save(newUser);
        ApplicationUserDTO userDTO = modelMapper.map(createdUser, ApplicationUserDTO.class);
        return userDTO;
    }
}

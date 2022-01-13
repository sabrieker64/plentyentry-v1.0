package at.commodussolutions.plentyentry.user.userdata.service.impl;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import at.commodussolutions.plentyentry.user.userdata.validations.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Author: @Eker
 */

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;

    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long id) {
        return repository.getById(id);
    }

    @Override
    public User registerNewUser(User user) {
        boolean isValidEmail = emailValidator.test(user.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email is not valid");
        }
        return signUpUser(user);
    }


    public User signUpUser(User user) {
        boolean userExists = repository.findByEmail(user.getEmail()).isPresent();
        if(userExists) {
            throw new IllegalStateException("Email wird schon verwendet");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        //TODO: send confirmation token
        return repository.save(user);
    }


    private final static String USER_NOT_FOUND = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }
}

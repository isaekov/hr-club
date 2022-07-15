package ru.hwru.integration.service.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.Authority;
import ru.hwru.integration.repository.AuthorityRepository;
import ru.hwru.integration.entity.User;
import ru.hwru.integration.repository.UserRepository;

import javax.management.relation.RoleNotFoundException;
import javax.security.auth.message.callback.PasswordValidationCallback;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserAuthService implements UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    private final BCryptPasswordEncoder bcryptEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserAuthService(BCryptPasswordEncoder bcryptEncoder, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.bcryptEncoder = bcryptEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public User saveNewUser(User user) throws Exception {

        if (user == null) {
            throw new UsernameNotFoundException("Не заполнены поля");
        }

        if (user.getPassword().isEmpty()) {
            throw new Exception("Пароль не должен быть пустым");
        }

        if (!user.getPassword().equals(user.passwordConfirm)) {
            throw new Exception("Пароли не совпадают");
        }

        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            throw new Exception("Пользователь с такой почтой уже существует");
        }

        System.out.println(userOptional);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Authority authority = authorityRepository.findByAuthority(ADMIN_ROLE);
        user.setAuthorities(Collections.singletonList(authority));
        return this.userRepository.saveAndFlush(user);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> blogUser = userRepository.findByUsername(email);
        if (blogUser.isPresent()) {
            return blogUser.get();
        } else {
            throw new UsernameNotFoundException("No user found with username " + email);
        }
    }

    public User currentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (authentication.getPrincipal() == "anonymousUser") {
//            return null;
//        }



        return userRepository.findByUsername("").get();
    }
}

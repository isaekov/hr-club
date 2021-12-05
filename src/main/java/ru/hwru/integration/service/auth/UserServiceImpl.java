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
import ru.hwru.integration.repository.BlogUserRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private final BCryptPasswordEncoder bcryptEncoder;
    private final BlogUserRepository blogUserRepository;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(BCryptPasswordEncoder bcryptEncoder, BlogUserRepository blogUserRepository, AuthorityRepository authorityRepository) {
        this.bcryptEncoder = bcryptEncoder;
        this.blogUserRepository = blogUserRepository;
        this.authorityRepository = authorityRepository;
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return blogUserRepository.findByUsername(email);
    }

    @Override
    public User saveNewUser(User blogUser) throws RoleNotFoundException {

        blogUser.setPassword(bcryptEncoder.encode(blogUser.getPassword()));
        blogUser.setEnabled(true);
        Authority authority = authorityRepository.findByAuthority(DEFAULT_ROLE);
        blogUser.setAuthorities(Collections.singletonList(authority));
        return this.blogUserRepository.saveAndFlush(blogUser);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> blogUser = blogUserRepository.findByUsername(email);
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



        return blogUserRepository.findByUsername("").get();
    }
}

package ru.hwru.integration.service.auth;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hwru.integration.dto.UserRegistration;
import ru.hwru.integration.entity.Role;
import ru.hwru.integration.entity.User;
import ru.hwru.integration.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User save(UserRegistration userRegistration) {

        User user = new User();
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setEmail(userRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(
                userRegistration.getPassword()
        ));
        user.setRoles(Arrays.asList(
                new Role("ROLE_USER"),
                new Role("ROLE_ADMIN")
        ));
        return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("invalid user name or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolGrantedAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolGrantedAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }

    public User getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return userRepository.findByEmail(((UserDetails) principal).getUsername());

        // principal object is either null or represents anonymous user -
        // neither of which our domain User object can represent - so return null
        return null;
    }

}

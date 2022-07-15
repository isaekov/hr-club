package ru.hwru.integration.service.auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.hwru.integration.entity.User;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByEmail(String email);

    User saveNewUser(User blogUser) throws Exception;

    User currentUser();

}

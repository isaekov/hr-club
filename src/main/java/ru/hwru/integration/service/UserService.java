package ru.hwru.integration.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.hwru.integration.dto.UserRegistration;
import ru.hwru.integration.entity.User;

@Service
public interface UserService extends UserDetailsService {
    User save(UserRegistration userRegistration);
}
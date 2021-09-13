package ru.hwru.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hwru.integration.entity.User;

import java.util.Optional;

@Repository
public interface BlogUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

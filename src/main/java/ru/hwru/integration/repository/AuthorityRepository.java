package ru.hwru.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hwru.integration.entity.Authority;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByAuthority(String authority);

//    Optional<Authority> findByAuthority(String authority);

}

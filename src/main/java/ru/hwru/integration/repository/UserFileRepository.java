package ru.hwru.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.UserFile;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {
}

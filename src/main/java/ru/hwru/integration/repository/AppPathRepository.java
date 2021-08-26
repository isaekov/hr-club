package ru.hwru.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.AppPath;

public interface AppPathRepository extends JpaRepository<AppPath, Integer> {
}

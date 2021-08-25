package ru.hwru.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}

package ru.hwru.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.AppPath;

import java.util.List;

public interface AppPathRepository extends JpaRepository<AppPath, Integer> {

    List<AppPath> findAllByParentId(int parentId);
}

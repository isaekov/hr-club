package ru.hwru.integration.service.settings;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.AppPath;

@Service
public interface PathService {

    void save(AppPath appPath);
}

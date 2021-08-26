package ru.hwru.integration.service.settings;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.AppPath;
import ru.hwru.integration.repository.AppPathRepository;

@Service
public class PathServiceImp implements PathService {

    private final AppPathRepository pathRepository;

    public PathServiceImp(AppPathRepository pathRepository) {
        this.pathRepository = pathRepository;
    }

    @Override
    public void save(AppPath appPath) {
        pathRepository.save(appPath);
    }
}

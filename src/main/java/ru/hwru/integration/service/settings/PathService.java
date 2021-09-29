package ru.hwru.integration.service.settings;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.AppPath;

import java.util.List;
import java.util.Map;

@Service
public interface PathService {

    void save(AppPath appPath);

    Map<Integer, String> getParent(List<AppPath> appPathList);
}

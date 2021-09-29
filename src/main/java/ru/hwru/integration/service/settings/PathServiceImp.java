package ru.hwru.integration.service.settings;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.AppPath;
import ru.hwru.integration.repository.AppPathRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<Integer, String> getParent(List<AppPath> appPathList) {

        Map<Integer, String > list = new HashMap<>();
        appPathList.forEach(v -> appPathList.forEach(pV -> {
            if (v.getParentId() == pV.getId()) {
                list.put(v.getId(), pV.getName());
            }
        }));
        return list;
    }

}

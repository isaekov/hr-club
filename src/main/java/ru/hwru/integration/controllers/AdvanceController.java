package ru.hwru.integration.controllers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.hwru.integration.entity.AppPath;
import ru.hwru.integration.repository.AppPathRepository;

import java.util.*;

@ControllerAdvice
public class AdvanceController {

    private Map<AppPath, List<AppPath>> appPathMap;

    private int count = 0;

    private final AppPathRepository appPathRepository;

    public AdvanceController(AppPathRepository appPathRepository, AppPathRepository pathRepository) {
        this.appPathRepository = appPathRepository;
    }


    @ModelAttribute("settings")
    public Map<AppPath, List<AppPath>> set() {

        if (appPathMap == null || count > 10) {
            appPathMap = new LinkedHashMap<>();
            for (AppPath app : appPathRepository.findAllByParentId(0)) {
                if(!appPathRepository.findAllByParentId(app.getId()).isEmpty()) {
                    appPathMap.put(
                            app,
                            appPathRepository.findAllByParentId(app.getId())
                    );
                }
            }
        }
        count++;
        return appPathMap;
    }

}

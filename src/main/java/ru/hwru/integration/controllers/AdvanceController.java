package ru.hwru.integration.controllers;


import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.hwru.integration.entity.AppPath;
import ru.hwru.integration.repository.AppPathRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.*;

@ControllerAdvice
public class AdvanceController {

    private Map<AppPath, List<AppPath>> appPathMap;

    private int count = 0;

    private final AppPathRepository appPathRepository;

    public AdvanceController(AppPathRepository appPathRepository, AppPathRepository pathRepository) {
        this.appPathRepository = appPathRepository;
    }


    @SneakyThrows
    @ModelAttribute("settings")
    public Map<AppPath, List<AppPath>> set() {

        if (appPathMap == null || count > 10) {
            count = 0;
            appPathMap = new LinkedHashMap<>();
            List<AppPath> allByParentId = appPathRepository.findAllByParentId(0);
            for (AppPath app : allByParentId) {
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

    @ModelAttribute("url")
    public String url(HttpServletRequest request) {
        return request.getRequestURI();
    }
    @ModelAttribute("currentUrl")
    public String currentUrl(HttpServletRequest request) {

        String[] a = request.getRequestURI().split("/");
        String tmp = "";
        for (String currentUrl :
             a) {
            tmp = currentUrl;
        }
        return tmp;
    }

    @ModelAttribute("list")
    public Map<String, String> list() {
        Map<String, String > map = new LinkedHashMap<>();
        appPathRepository.findAll().forEach(v -> {
            map.put(v.getPath(), v.getName());
        });
        return map;
    }

}

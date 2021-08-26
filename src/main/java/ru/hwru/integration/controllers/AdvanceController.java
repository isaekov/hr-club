package ru.hwru.integration.controllers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.hwru.integration.entity.AppPath;
import ru.hwru.integration.repository.AppPathRepository;

import java.util.List;

@ControllerAdvice
public class AdvanceController {

    private List<AppPath> paths;

    private int count = 0;


    private final AppPathRepository pathRepository;

    public AdvanceController(AppPathRepository pathRepository) {
        this.pathRepository = pathRepository;
    }


    @ModelAttribute("settings")
    public List<AppPath> set() {
        if (paths == null || count > 10) {
            count = 0;
            System.out.println("Hello");
            paths = pathRepository.findAll();
        }
        count++;
        return paths;
    }

}

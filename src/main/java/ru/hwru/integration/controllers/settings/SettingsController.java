package ru.hwru.integration.controllers.settings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hwru.integration.entity.AppPath;
import ru.hwru.integration.repository.AppPathRepository;
import ru.hwru.integration.service.settings.PathService;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final AppPathRepository appPathRepository;
    private final PathService pathService;

    public SettingsController(AppPathRepository appPathRepository, PathService pathService) {
        this.appPathRepository = appPathRepository;
        this.pathService = pathService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pathList", appPathRepository.findAll());
        model.addAttribute("appPath", new AppPath());
        return "settings/index";
    }

    @PostMapping
    public String appPathSubmit(@ModelAttribute AppPath appPath) {

        pathService.save(appPath);
        return "redirect:/settings";
    }

}

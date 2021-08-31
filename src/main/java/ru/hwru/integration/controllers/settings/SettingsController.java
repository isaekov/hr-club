package ru.hwru.integration.controllers.settings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/path")
    public String index(Model model) {
        model.addAttribute("pathList", appPathRepository.findAll());
        model.addAttribute("appPath", new AppPath());
        return "settings/index";
    }

    @PostMapping("/path")
    public String appPathSubmit(@ModelAttribute AppPath appPath) {
        pathService.save(appPath);
        return "redirect:/settings";
    }

    @GetMapping("/path/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        AppPath path = appPathRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid path id: " + id));
        model.addAttribute("pathList", appPathRepository.findAll());
        model.addAttribute("path", path);
        return "settings/path-update";
    }

    @PostMapping("/path/update/{id}")
    public String updateForm(@PathVariable("id") int id, @Validated AppPath path, BindingResult result) {
        if (result.hasErrors()) {
            path.setId(id);
            return "settings/path-update";
        }
        appPathRepository.save(path);
        return "redirect:/settings/path";
    }

    @GetMapping("/path/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        AppPath path = appPathRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid path Id:" + id));
        appPathRepository.delete(path);
        return "redirect:/settings/path";
    }



}

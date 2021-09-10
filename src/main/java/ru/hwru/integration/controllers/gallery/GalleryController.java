package ru.hwru.integration.controllers.gallery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hwru.integration.service.upload.FileStorageService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    private final FileStorageService fileStorageService;

    public GalleryController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public String gallery() {
        return "gallery/index";
    }


    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("fileLists", fileStorageService.loadF());
        return "gallery/list";
    }
}

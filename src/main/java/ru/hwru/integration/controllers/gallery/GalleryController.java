package ru.hwru.integration.controllers.gallery;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.hwru.integration.controllers.upload.FileUploadController;
import ru.hwru.integration.entity.UserFile;
import ru.hwru.integration.service.upload.FileStorageService;
import ru.hwru.integration.tmp.TController;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> file = fileStorageService.loadUserFile().stream().map(
                path -> MvcUriComponentsBuilder.fromMethodName(GalleryController.class,
                        "getFile", path.getGenerateName()).build().toUri().toString())
                .collect(Collectors.toList());
        model.addAttribute("files", file);
        model.addAttribute("fil", fileStorageService.loadUserFile());
        return "gallery/list";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = fileStorageService.load(filename);
        UserFile userFile = fileStorageService.loadUserFile().stream().filter(f -> f.getGenerateName().equals(file.getFilename())).findFirst().get();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename*=UTF-8''" + URLEncoder.encode(userFile.getOriginName(), "UTF-8")).body(file);
    }
}

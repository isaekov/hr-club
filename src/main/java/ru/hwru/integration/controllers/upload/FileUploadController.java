package ru.hwru.integration.controllers.upload;

import org.springframework.core.io.Resource;;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.hwru.integration.dto.FileInfo;
import ru.hwru.integration.dto.upload.ResponseMessage;
import ru.hwru.integration.entity.File;
import ru.hwru.integration.entity.User;
import ru.hwru.integration.repository.UserRepository;
import ru.hwru.integration.service.auth.UserService;
import ru.hwru.integration.service.upload.FileStorageService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/file")

public class FileUploadController {

    private final FileStorageService fileStorageService;

    private final UserService userService;
    private final UserRepository userRepository;

    public FileUploadController(FileStorageService fileStorageService, UserService userService, UserRepository userRepository) {
        this.fileStorageService = fileStorageService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String index(Model model) {
        User user = userRepository.findByEmail(userService.getCurrentUser().getEmail());
        model.addAttribute("files", user);
        return "upload/index";
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileStorageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + e.getMessage() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<Set<File>> getListFiles() {
        return ResponseEntity.status(HttpStatus.OK).body(fileStorageService.loadF());
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileStorageService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
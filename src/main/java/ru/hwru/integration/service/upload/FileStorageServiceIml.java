package ru.hwru.integration.service.upload;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.hwru.integration.entity.File;
import ru.hwru.integration.entity.User;
import ru.hwru.integration.repository.FileRepository;
import ru.hwru.integration.service.auth.UserService;


import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class FileStorageServiceIml implements FileStorageService {


    private String path = "uploads";

    private final Path root = Paths.get("uploads");

    private final FileRepository fileRepository;

    private final UserService userService;

    public FileStorageServiceIml(FileRepository fileRepository, UserService userService) {
        this.fileRepository = fileRepository;
        this.userService = userService;
    }

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось инициализировать папку для загрузки!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            User user = userService.getCurrentUser();




            java.io.File file1 = new java.io.File(path+"/"+user.getId());

            if (!file1.exists()) {
                file1.mkdir();
            }
            System.out.println(path+"/"+user.getId());
                    String generateFilename = UUID.randomUUID().toString();
                    String originalFilename = Objects.requireNonNull(file.getOriginalFilename());


                    fileRepository.save(new File(
                            user.getId(),
                            originalFilename,
                            generateFilename,
                            FilenameUtils.getExtension(originalFilename)
                    ));
                    Files.copy(file.getInputStream(), Paths.get(path+"/"+user.getId()).resolve(generateFilename),
                            StandardCopyOption.REPLACE_EXISTING);




        } catch (Exception e) {
            throw new RuntimeException("Не удалось сохранить файл. Ошибка: " + e.getMessage());
        }

    }





    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}

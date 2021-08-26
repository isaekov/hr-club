package ru.hwru.integration.service.upload;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import ru.hwru.integration.entity.File;
import ru.hwru.integration.repository.FileRepository;
import ru.hwru.integration.service.auth.UserService;


import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class FileStorageServiceIml implements FileStorageService {


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

    /**
     * Сохраняет файл на диск, и хранит реальное имя в базе
     */
    @Override
    public void save(MultipartFile file) {
        try {
            File prepareFile = prepareNameUserFile(file);
            saveDiskFile(prepareFile, file);
            fileRepository.save(prepareFile);

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


    private File prepareNameUserFile(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return new File(
                userService.getCurrentUser().getId(),
                file.getOriginalFilename(),
                UUID.randomUUID() + "." + extension,
                extension
        );
    }

    private void saveDiskFile(File prepareFile, MultipartFile file) throws Exception {
        java.io.File directory = new java.io.File("uploads/" + prepareFile.getUserId());
        if (!directory.exists()) {
            if (directory.mkdir()) {
                throw new NotDirectoryException("Ошбика при создание директории ");
            }
        }
        Files.copy(
                file.getInputStream(),
                Paths.get(directory.getPath())
                        .resolve(prepareFile.getGenerateName()),
                StandardCopyOption.REPLACE_EXISTING
        );
    }
}

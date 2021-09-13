package ru.hwru.integration.service.upload;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import ru.hwru.integration.entity.UserFile;
import ru.hwru.integration.repository.UserFileRepository;
import ru.hwru.integration.service.auth.UserService;
import ru.hwru.integration.tmp.StorageException;


import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class FileStorageServiceIml implements FileStorageService {



    private final UserFileRepository userFileRepository;

    private final UserService userService;

    public FileStorageServiceIml(UserFileRepository userFileRepository, UserService userService) {
        this.userFileRepository = userFileRepository;
        this.userService = userService;
    }

    private Path rootLocation() {

        File file =  new File("uploads/" + this.userService.getCurrentUser().getId());
        if (!file.exists()) {
            if (file.mkdir()) {
                // todo: release
            }
        }

        return Paths.get(file.getPath());
    }


    /**
     * Сохраняет файл на диск, и хранит реальное имя в базе
     */
    @Override
    public void store(MultipartFile file) {
        try {
            UserFile prepareFile = prepareNameUserFile(file);
            saveDiskFile(prepareFile, file);
            userFileRepository.save(prepareFile);

        } catch (Exception e) {
            throw new RuntimeException("Не удалось сохранить файл. Ошибка: " + e.getMessage());
        }
    }


    @Override
    public Resource load(String filename) {
        try {
            Path root = Paths.get("uploads/"+userService.getCurrentUser().getId());
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Не удалось прочитать файл!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation().toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation(), 1)
                    .filter(path -> !path.equals(this.rootLocation()))
                    .map(path -> this.rootLocation().relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }


    public Set<UserFile> loadUserFile() {
        return userService.getCurrentUser().getFiles();
    }


    private UserFile prepareNameUserFile(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return new UserFile(
                userService.getCurrentUser(),
                file.getOriginalFilename(),
                UUID.randomUUID() + "." + extension,
                extension
        );
    }

    private void saveDiskFile(UserFile prepareFile, MultipartFile file) throws Exception {
        Files.copy(
                file.getInputStream(),
                this.rootLocation().resolve(prepareFile.getGenerateName()),
                StandardCopyOption.REPLACE_EXISTING
        );
    }
}

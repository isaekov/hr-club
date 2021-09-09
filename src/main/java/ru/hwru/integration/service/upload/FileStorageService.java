package ru.hwru.integration.service.upload;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hwru.integration.entity.File;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;
@Service
public interface FileStorageService {



    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

    public Set<File> loadF();
}

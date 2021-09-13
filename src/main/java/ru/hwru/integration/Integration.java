package ru.hwru.integration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.hwru.integration.service.upload.FileStorageService;
import ru.hwru.integration.tmp.StorageService;

import javax.annotation.Resource;

@SpringBootApplication
public class Integration implements CommandLineRunner {

    @Resource
    FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(Integration.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {


    }
}

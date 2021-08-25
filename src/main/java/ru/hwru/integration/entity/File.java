package ru.hwru.integration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class File {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String originName;

    private String generateName;

    private String extension;

    public File(Long userId, String originName, String generateName, String extension) {
        this.userId = userId;
        this.originName = originName;
        this.generateName = generateName;
        this.extension = extension;
    }
}

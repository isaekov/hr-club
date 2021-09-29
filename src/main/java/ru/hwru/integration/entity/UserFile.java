package ru.hwru.integration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
public class UserFile {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String originName;

    private String generateName;

    private String extension;

    public UserFile(User user, String originName, String generateName, String extension) {
        this.user = user;
        this.originName = originName;
        this.generateName = generateName;
        this.extension = extension;
    }

    public UserFile(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getGenerateName() {
        return generateName;
    }

    public void setGenerateName(String generateName) {
        this.generateName = generateName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "UserFile{" +
                "id=" + id +
                ", user=" + user +
                ", originName='" + originName + '\'' +
                ", generateName='" + generateName + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}

package ru.hwru.integration.entity;


import javax.persistence.*;

@Entity
public class AppPath {

    @Id
    @GeneratedValue
    private int id;

    private String path;

    private String name;

    private int parentId;

    public AppPath() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int icon) {
        this.parentId = icon;
    }
}

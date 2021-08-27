package ru.hwru.integration.entity;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class AppPath {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "path")
    private String path;

    @Column(name = "name")
    private String name;

    @Column(name = "attr")
    private String title;

    @Column(name = "color")
    private String color;

    @ColumnDefault("0")
    private boolean isUse;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String attr) {
        this.title = attr;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int icon) {
        this.parentId = icon;
    }
}

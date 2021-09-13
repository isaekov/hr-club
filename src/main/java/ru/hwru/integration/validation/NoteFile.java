package ru.hwru.integration.validation;


import javax.validation.constraints.NotNull;

public class NoteFile {


    public int type;


    @NotNull
    public String name;

    public int parent;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}

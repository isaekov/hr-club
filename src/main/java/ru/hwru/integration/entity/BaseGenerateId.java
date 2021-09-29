package ru.hwru.integration.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BaseGenerateId {
    @Id
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

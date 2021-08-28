package ru.hwru.integration.entity.finance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Products {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}

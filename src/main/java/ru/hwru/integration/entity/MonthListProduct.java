package ru.hwru.integration.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MonthListProduct {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private int count;

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    private boolean isUse;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public MonthListProduct() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

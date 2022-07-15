package ru.hwru.integration.entity.ecommerce;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String name;

    public String description;

    public double price;

    public double sale;

    public boolean isRemove;

    public boolean isPublication;

    public Date createAt;


    // relations

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    public Set<ProductOption> options;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    public Set<ProductImage> productImages;

    public int getId() {
        return this.id;
    }

}

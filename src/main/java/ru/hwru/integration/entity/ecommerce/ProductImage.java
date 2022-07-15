package ru.hwru.integration.entity.ecommerce;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String name;

    public String defaultName;

    public String path;

    public boolean remove;

    @ManyToOne
    public Product product;

}

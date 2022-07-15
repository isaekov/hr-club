package ru.hwru.integration.entity.ecommerce;

import javax.persistence.*;

@Entity
@Table(name = "product_options")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    public Product product;

    public String field;

    public String value;

}

package ru.hwru.integration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price = 0;

    @Column(name = "count", nullable = false, columnDefinition = "int default 0")
    private int count;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    public Set<MonthListProduct> getMonthListProducts() {
        return monthListProducts;
    }

    public void setMonthListProducts(Set<MonthListProduct> monthListProducts) {
        this.monthListProducts = monthListProducts;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MonthListProduct> monthListProducts = new HashSet<>();

    public Product(String name, int price, ProductCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }



    public Product() {}

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    //
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return category.equals(product.category);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(category);
//    }
}

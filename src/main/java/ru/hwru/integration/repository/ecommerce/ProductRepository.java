package ru.hwru.integration.repository.ecommerce;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.ecommerce.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

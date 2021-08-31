package ru.hwru.integration.repository.finance;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

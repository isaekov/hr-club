package ru.hwru.integration.repository.finance;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}

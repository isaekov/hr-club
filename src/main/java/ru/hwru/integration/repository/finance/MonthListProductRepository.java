package ru.hwru.integration.repository.finance;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hwru.integration.entity.MonthListProduct;

public interface MonthListProductRepository extends JpaRepository<MonthListProduct, Long> {
}

package ru.hwru.integration.service.finance;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.ProductCategory;

@Service
public interface FinanceService {
    void add(ProductCategory category);
}

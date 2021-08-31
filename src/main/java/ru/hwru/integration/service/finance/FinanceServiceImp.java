package ru.hwru.integration.service.finance;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.ProductCategory;
import ru.hwru.integration.repository.finance.ProductCategoryRepository;
import ru.hwru.integration.repository.finance.ProductRepository;

@Service
public class FinanceServiceImp implements FinanceService{

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public FinanceServiceImp(
            ProductRepository productRepository,
            ProductCategoryRepository productCategoryRepository
    ) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public void add(ProductCategory category) {
        productCategoryRepository.save(category);
    }
}

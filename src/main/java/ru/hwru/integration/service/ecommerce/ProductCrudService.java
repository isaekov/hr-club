package ru.hwru.integration.service.ecommerce;

import org.springframework.stereotype.Service;
import ru.hwru.integration.entity.ecommerce.Product;
import ru.hwru.integration.repository.ecommerce.ProductRepository;

@Service
public class ProductCrudService implements ProductService {


    private final ProductRepository productRepository;

    public ProductCrudService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public int remove(int id) {
        return 0;
    }

    @Override
    public int update(int id) {
        return 0;
    }

    @Override
    public void getFullInformation() {

    }
}

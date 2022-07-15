package ru.hwru.integration.service.ecommerce;

import ru.hwru.integration.entity.ecommerce.Product;

public interface ProductService {

    void save(Product product);

    int remove(int id);

    int update(int id);

    void getFullInformation();



}

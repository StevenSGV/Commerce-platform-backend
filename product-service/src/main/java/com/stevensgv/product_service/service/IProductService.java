package com.stevensgv.product_service.service;

import com.stevensgv.product_service.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> getProductList();

    Product findProductById(Long id);

    void saveProduct(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}

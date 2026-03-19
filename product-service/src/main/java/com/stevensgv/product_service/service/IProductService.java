package com.stevensgv.product_service.service;

import com.stevensgv.product_service.model.Product;

import java.util.List;
import java.util.Set;

public interface IProductService {

    List<Product> getProductList();

    Product findProductById(Long id);

    List<Product> validateProductList(Set<Long> listProductIds);

    void saveProduct(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}

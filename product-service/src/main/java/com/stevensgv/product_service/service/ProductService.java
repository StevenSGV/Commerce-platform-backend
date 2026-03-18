package com.stevensgv.product_service.service;

import com.stevensgv.product_service.exception.NotFoundException;
import com.stevensgv.product_service.model.Product;
import com.stevensgv.product_service.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final IProductRepository productRepository;

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found."));
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        Product findProduct = this.findProductById(id);

        findProduct.setName(product.getName());
        findProduct.setDescription(product.getDescription());
        findProduct.setPrice(product.getPrice());

        productRepository.save(findProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

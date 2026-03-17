package com.stevensgv.product_service.controller;

import com.stevensgv.product_service.model.Product;
import com.stevensgv.product_service.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public List<Product> getProductList() {
        return productService.getProductList();
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        productService.saveProduct(product);
        return productService.getProductById(product.getId());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @Valid @RequestBody Product product) {
        productService.updateProduct(id, product);
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

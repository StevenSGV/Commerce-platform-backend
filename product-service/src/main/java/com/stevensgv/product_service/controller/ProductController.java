package com.stevensgv.product_service.controller;

import com.stevensgv.product_service.model.Product;
import com.stevensgv.product_service.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public List<Product> getProductList() {
        return productService.getProductList();
    }

    @PostMapping("/list")
    public List<Product> validateProductList(@RequestBody Set<Long> listProductIds) {
        return productService.validateProductList(listProductIds);
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        productService.saveProduct(product);
        return productService.findProductById(product.getId());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @Valid @RequestBody Product product) {
        productService.updateProduct(id, product);
        return productService.findProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

package com.stevensgv.order_service.service;

import com.stevensgv.order_service.dto.ProductDTO;
import com.stevensgv.order_service.exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductCircuitBreaker {

    private final IProductFeign productFeign;

    @CircuitBreaker(name = "productService", fallbackMethod = "validateProductsFallback")
    public List<ProductDTO> validateProductsCircuitBreaker(Set<Long> productIds) {
        return productFeign.validateProductList(productIds);
    }

    public List<ProductDTO> validateProductsFallback(Set<Long> productIds, Throwable throwable) {
        throw new ServiceUnavailableException("Product service is currently unavailable.");
    }
}

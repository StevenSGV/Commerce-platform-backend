package com.stevensgv.cart_service.service;

import com.stevensgv.cart_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(name = "product-service")
public interface IProductFeign {

    @PostMapping("/api/products/list")
    List<ProductDTO> validateProductList(@RequestBody Set<Long> listProductIds);
}

package com.stevensgv.cart_service.controller;

import com.stevensgv.cart_service.dto.ProductDTO;
import com.stevensgv.cart_service.model.Cart;
import com.stevensgv.cart_service.service.ICartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final ICartService cartService;

    @GetMapping
    public List<Cart> getCartList() {
        return cartService.getCartList();
    }

    @PostMapping
    public Cart createCart(@Valid @RequestBody Cart cart) {
        cartService.saveCart(cart);
        return cartService.findCartById(cart.getId());
    }

    @PutMapping("/{id}")
    public Cart updateCart(@Valid @PathVariable Long id,
                           @RequestBody Cart cart) {
        cartService.saveCart(cart);
        return cartService.findCartById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCartById(id);
        return ResponseEntity.noContent().build();
    }
}

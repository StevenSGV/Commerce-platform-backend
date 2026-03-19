package com.stevensgv.cart_service.service;

import com.stevensgv.cart_service.dto.ProductDTO;
import com.stevensgv.cart_service.model.Cart;

import java.util.List;

public interface ICartService {

    List<Cart> getCartList();

    Cart findCartById(Long id);

    ProductDTO findProductById(Long id);

    void saveCart(Cart cart);

    void updateCart(Long id, Cart cart);

    void deleteCartById(Long id);
}

package com.stevensgv.cart_service.service;

import com.stevensgv.cart_service.exception.NotFoundException;
import com.stevensgv.cart_service.model.Cart;
import com.stevensgv.cart_service.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ICartRepository cartRepository;

    @Override
    public List<Cart> getCartList() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void updateCart(Long id, Cart cart) {
        Cart cartFound = this.findCartById(id);

        cartFound.setUserId(cart.getUserId());
        cartFound.setCartItemList(cart.getCartItemList());

        cartRepository.save(cartFound);
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }
}

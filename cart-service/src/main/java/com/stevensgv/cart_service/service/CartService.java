package com.stevensgv.cart_service.service;

import com.stevensgv.cart_service.dto.ProductDTO;
import com.stevensgv.cart_service.exception.NotFoundException;
import com.stevensgv.cart_service.model.Cart;
import com.stevensgv.cart_service.model.CartItem;
import com.stevensgv.cart_service.repository.ICartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ICartRepository cartRepository;
    private final ProductCircuitBreaker productCircuitBreaker;

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
         Set<Long> listIdProducts = cart.getCartItemList()
                 .stream()
                 .map(CartItem::getProductId)
                 .collect(Collectors.toSet());

         List<ProductDTO> existingProducts = productCircuitBreaker.validateProductsCircuitBreaker(listIdProducts);

         if (existingProducts.size() != listIdProducts.size()) {
             throw new NotFoundException("One or more requested products were not found.");
         }

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

package com.cg.pickels.TangyTales.service;

import com.cg.pickels.TangyTales.entity.CartItem;
import com.cg.pickels.TangyTales.entity.Product;
import com.cg.pickels.TangyTales.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Override
    public void addToCart(Product product, int quantity) {
        // Check if product already exists in cart
        List<CartItem> items = cartRepo.findAll();
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                cartRepo.save(item);
                return;
            }
        }
        CartItem cartItem = new CartItem();
        cartRepo.save(cartItem);
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartRepo.findAll();
    }

    @Override
    public void updateQuantity(Long cartItemId, int quantity) {
        CartItem item = cartRepo.findById(cartItemId).orElse(null);
        if (item != null) {
            item.setQuantity(quantity);
            cartRepo.save(item);
        }
    }

    @Override
    public void removeItem(Long cartItemId) {
        cartRepo.deleteById(cartItemId);
    }

    @Override
    public double getTotalCartPrice() {
        return cartRepo.findAll().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    @Override
    public void clearCart() {
        cartRepo.deleteAll();
    }
}


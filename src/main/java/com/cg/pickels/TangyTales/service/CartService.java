package com.cg.pickels.TangyTales.service;

import com.cg.pickels.TangyTales.entity.CartItem;
import com.cg.pickels.TangyTales.entity.Product;

import java.util.List;

public interface CartService {
    void addToCart(Product product, int quantity);
    List<CartItem> getCartItems();
    void updateQuantity(Long cartItemId, int quantity);
    void removeItem(Long cartItemId);
    double getTotalCartPrice();
    void clearCart();
}


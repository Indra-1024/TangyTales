package com.cg.pickels.TangyTales.controller;

import com.cg.pickels.TangyTales.entity.CartItem;
import com.cg.pickels.TangyTales.entity.Product;
import com.cg.pickels.TangyTales.service.CartService;
import com.cg.pickels.TangyTales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    // Show cart
    @GetMapping
    public String viewCart(Model model) {
        List<CartItem> items = cartService.getCartItems();
        double total = cartService.getTotalCartPrice();
        model.addAttribute("cartItems", items);
        model.addAttribute("totalPrice", total);
        return "cart"; // cart.html
    }

    // Show Add-to-Cart form for a product
    @GetMapping("/add/{productId}")
    public String showAddToCartForm(@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return "redirect:/products"; // fallback if product not found
        }
        model.addAttribute("product", product);
        return "addtocart"; // ✅ matches addtocart.html
    }

    // Handle Add-to-Cart form submission
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam int quantity) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartService.addToCart(product, quantity);
        }
        return "redirect:/cart";
    }

    // Update quantity
    @PostMapping("/update/{cartItemId}")
    public String updateQuantity(@PathVariable Long cartItemId,
                                 @RequestParam int quantity) {
        cartService.updateQuantity(cartItemId, quantity);
        return "redirect:/cart";
    }

    // Remove item
    @GetMapping("/remove/{cartItemId}")
    public String removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return "redirect:/cart";
    }

    // Clear cart
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}

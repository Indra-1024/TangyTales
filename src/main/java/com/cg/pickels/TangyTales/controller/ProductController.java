package com.cg.pickels.TangyTales.controller;

import com.cg.pickels.TangyTales.entity.Product;
import com.cg.pickels.TangyTales.repository.ProductRepo;
import com.cg.pickels.TangyTales.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private CartService cartService;

    // Show all products
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products"; // products.html
    }

    // Show form to add product (Admin use)
    @GetMapping("/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct"; // addproduct.html
    }

    // Save new product
    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    // ✅ Add product to cart directly (default quantity = 1)
    @GetMapping("/add-to-cart/{productId}")
    public String addProductToCart(@PathVariable Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            cartService.addToCart(product, 1); // add with default quantity = 1
        }
        return "redirect:/cart"; // redirect user to cart page
    }
}

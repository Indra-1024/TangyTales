package com.cg.pickels.TangyTales.service;

import com.cg.pickels.TangyTales.entity.Product;

import java.util.List;

public interface ProductService {
    // Save or update product
    Product saveProduct(Product product);

    // Get product by ID
    Product getProductById(Long id);

    // Get all products
    List<Product> getAllProducts();

    // Delete product by ID
    void deleteProduct(Long id);
}

package com.cg.pickels.TangyTales.service;

import com.cg.pickels.TangyTales.entity.*;
import com.cg.pickels.TangyTales.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartRepo cartRepo;

    @Override
    public CustomerOrder placeOrder(AppUser user) {
        List<CartItem> cartItems = cartRepo.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty, cannot place order!");
        }

        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        CustomerOrder order = new CustomerOrder();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalPrice(totalPrice);
        order.setUser(user);
        order.setItems(cartItems);

        CustomerOrder savedOrder = orderRepo.save(order);

        // Clear cart after placing order
        cartRepo.deleteAll(cartItems);

        return savedOrder;
    }

    @Override
    public List<CustomerOrder> getOrdersByUser(AppUser user) {
        return orderRepo.findByUser(user);
    }

    @Override
    public CustomerOrder getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public CustomerOrder saveOrder(CustomerOrder order) {
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }
        return orderRepo.save(order);
    }
}

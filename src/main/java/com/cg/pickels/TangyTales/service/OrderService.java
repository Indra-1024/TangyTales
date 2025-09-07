package com.cg.pickels.TangyTales.service;

import com.cg.pickels.TangyTales.entity.AppUser;
import com.cg.pickels.TangyTales.entity.CustomerOrder;

import java.util.List;

public interface OrderService {
    CustomerOrder placeOrder(AppUser user);        // From cart
    List<CustomerOrder> getOrdersByUser(AppUser user);
    CustomerOrder getOrderById(Long id);
    CustomerOrder saveOrder(CustomerOrder order);  // Manual save (from placeorder.html)
}

package com.cg.pickels.TangyTales.controller;

import com.cg.pickels.TangyTales.entity.AppUser;
import com.cg.pickels.TangyTales.entity.CustomerOrder;
import com.cg.pickels.TangyTales.service.CartService;
import com.cg.pickels.TangyTales.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    // === Show all orders for the logged-in user ===
    @GetMapping
    public String listOrders(Model model, Principal principal) {
        AppUser user = new AppUser();
        user.setId(1L); // TODO: Replace with logged-in user

        List<CustomerOrder> orders = orderService.getOrdersByUser(user);
        model.addAttribute("orders", orders);
        return "orders";
    }

    // === Show "place new order" form ===
    @GetMapping("/new")
    public String showPlaceOrderForm(Model model) {
        model.addAttribute("order", new CustomerOrder());
        return "placeorder";
    }

    // === Handle form submission for placing new order ===
    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("order") CustomerOrder order, Principal principal) {
        AppUser user = new AppUser();
        user.setId(1L); // TODO: Replace with logged-in user

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now()); // ✅ Date + Time
        orderService.saveOrder(order);

        return "redirect:/orders";
    }

    // === Place order directly from cart ===
    @PostMapping("/place")
    public String placeOrderFromCart(Principal principal) {
        AppUser user = new AppUser();
        user.setId(1L); // TODO: Replace with logged-in user

        orderService.placeOrder(user);
        return "redirect:/orders";
    }

    // === Order details ===
    @GetMapping("/details/{id}")
    public String orderDetails(@PathVariable Long id, Model model) {
        CustomerOrder order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "orderdetails";
    }

    // === Checkout page ===
    @GetMapping("/checkout")
    public String checkoutPage(Model model, Principal principal) {
        AppUser user = new AppUser();
        user.setId(1L); // TODO: Replace with logged-in user

        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalCartPrice());
        return "checkout"; // ✅ matches checkout.html
    }

    // === Handle Checkout Form Submission ===
    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute CustomerOrder order, Principal principal) {
        AppUser user = new AppUser();
        user.setId(1L); // TODO: Replace with logged-in user

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        orderService.saveOrder(order);

        return "redirect:/orders";
    }
}

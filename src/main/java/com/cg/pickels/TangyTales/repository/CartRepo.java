package com.cg.pickels.TangyTales.repository;

import com.cg.pickels.TangyTales.entity.AppUser;
import com.cg.pickels.TangyTales.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartItem, Long> {

    // 👇 Fix: added method to fetch cart items for a user
    List<CartItem> findByUser(AppUser user);
}

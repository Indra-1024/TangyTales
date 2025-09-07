package com.cg.pickels.TangyTales.repository;

import com.cg.pickels.TangyTales.entity.CustomerOrder;
import com.cg.pickels.TangyTales.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByUser(AppUser user);
}

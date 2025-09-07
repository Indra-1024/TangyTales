package com.cg.pickels.TangyTales.repository;

import com.cg.pickels.TangyTales.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
}

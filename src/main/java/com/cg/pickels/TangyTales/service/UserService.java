package com.cg.pickels.TangyTales.service;

import com.cg.pickels.TangyTales.entity.AppUser;
import com.cg.pickels.TangyTales.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register user
    public String registerUser(AppUser user) {
        // Check if email already exists
        if (userRepo.findByEmail(user.getEmail()) != null) {
            return "Email already exists!";
        }

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        userRepo.save(user);
        return "User registered successfully!";
    }

    // Find user by email (used for validation or login)
    public Optional<AppUser> findByEmail(String email) {
        return Optional.ofNullable(userRepo.findByEmail(email));
    }

    // Optional: Get user by ID
    public Optional<AppUser> findById(Long id) {
        return userRepo.findById(id);
    }

    // Optional: Get all users
    public Iterable<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

	public AppUser findByUsername(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}

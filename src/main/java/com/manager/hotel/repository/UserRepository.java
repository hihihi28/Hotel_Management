package com.manager.hotel.repository;

import com.manager.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA tự động tạo phương thức findByEmail từ tên phương thức
    User findByEmail(String email);
    User findByUsername(String username);
}
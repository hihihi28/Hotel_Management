package com.manager.hotel.service;

import com.manager.hotel.commons.Constant;
import com.manager.hotel.model.User;
import com.manager.hotel.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User registerUser(User user) {
        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // Tạo và lưu người dùng
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encodedPassword);
        newUser.setRole(Constant.Role.USER);
        newUser.setCreateDate(new Date());
        return userRepository.save(newUser);
    }
}

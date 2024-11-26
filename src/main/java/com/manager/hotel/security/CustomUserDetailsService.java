package com.manager.hotel.security;

import com.manager.hotel.model.User;
import com.manager.hotel.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new CustomUserDetails(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRole()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        List<String> roles = new ArrayList<>();
        roles.add(role);
        Collection< ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(r -> new SimpleGrantedAuthority(r))
                .collect(Collectors.toList());
        return mapRoles;
    }
}

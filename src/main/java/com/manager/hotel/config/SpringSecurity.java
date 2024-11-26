package com.manager.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            // Kiểm tra vai trò của người dùng
            authentication.getAuthorities().forEach(grantedAuthority -> {
                String role = grantedAuthority.getAuthority();
                try {
                    if ("ROLE_A".equals(role)) {
                        response.sendRedirect("/admin/admin-dashboard");
                    } else if ("ROLE_U".equals(role)) {
                        response.sendRedirect("/user/user-dashboard");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Bỏ qua CSRF cho các API endpoint nếu sử dụng REST API
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**")
                )
                // Cấu hình các yêu cầu bảo mật
                .authorizeRequests()
                .requestMatchers("/register", "/login", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/admin/**").hasRole("A")
                .requestMatchers("/user/**").hasRole("U")
                // Cho phép truy cập không cần đăng nhập đối với các trang sau
//                .requestMatchers("/","/login","/register", "/hotel", "/booking","/room", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()  // Cho phép tài nguyên tĩnh và trang login
                // Các yêu cầu còn lại phải đăng nhập
                .anyRequest().authenticated()
                .and()
                // Cấu hình trang đăng nhập tùy chỉnh
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .successHandler(successHandler())
                        .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()

                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        // Cấu hình user mặc định lưu tạm vào bộ nhớ thay vì database-
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser(User.builder()
                        .username("user@example.com")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER")
                        .build());

        // Trả về AuthenticationManager sau khi cấu hình
        return authenticationManagerBuilder.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}

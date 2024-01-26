package com.example.CarShopSber.Config;

import com.example.CarShopSber.Service.SecurityService.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Класс конфигурации spring security
 * @author Ilya
 * @version 1.0
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * @value Поле UserDetails
     * */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Функция конфигурации безопасности
     * @return Метод configure возвращает настроенный SecurityFilterChain
     * */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/profile/**", "/add_car","/car/add_car", "/favourite")
                        .authenticated()
                        .requestMatchers("/admin/**").hasRole("admin")
                        .requestMatchers("/car/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/sign_in")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/profile")
                        .failureUrl("/login_error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/sign_in")
                        .permitAll()
                );

        return http.build();
    }

    /**
     * @return Возвращает объект AuthenticationManager
     * */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * @return Bean, возвращающий объект BCryptPasswordEncoder()
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

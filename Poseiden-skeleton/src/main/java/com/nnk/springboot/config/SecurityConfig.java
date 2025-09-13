package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nnk.springboot.service.UserDetailService;

/**
 * Security configuration class.
 * Defines authentication (userDetailsService + password encoder)
 * and authorization rules (role-based access).
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	/**
     * Configure password encoding with BCrypt.
     *
     * @return the password encoder
     */
	@Autowired
    private UserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure the authentication manager with user details service
     * and password encoder.
     *
     * @param http HttpSecurity object
     * @return the authentication manager
     * @throws Exception if configuration fails
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    /**
     * Configure HTTP security: login, logout, and authorization rules.
     *
     * @param http HttpSecurity object
     * @return the security filter chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/app/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/user/**", "/admin/**").hasRole("ADMIN") // only for admin role
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/app/login")
                .loginProcessingUrl("/app/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/app/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/app/login?logout=true")
                .permitAll()
            )
            .exceptionHandling(ex -> ex.accessDeniedPage("/app/error"));

        return http.build();
    }

}

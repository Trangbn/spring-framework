package com.trangbn.springboot_application.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.headers(header -> header.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
        http
                // 🔹 Authorization rules
                .authorizeHttpRequests(requests -> requests
                        // Public endpoints
                        .requestMatchers("/", "/login", "/about", "/contact",
                                "/courses", "/holidays/**", "/assets/**", "/logout").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/displayProfile").authenticated()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/displayMessages").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        .requestMatchers( PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/public/**").permitAll()
                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )
                // 🔹 Form Login configuration
                //.formLogin(Customizer.withDefaults())  // For using default spring security login. Else need to define own controller and view
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll().defaultSuccessUrl("/dashboard", true).failureUrl("/login?error=true").permitAll()
                )
                // 🔹 Logout configuration
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")   // redirect after logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                ).httpBasic(Customizer.withDefaults())

                // 🔹 CSRF protection (enabled by default)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**").ignoringRequestMatchers(PathRequest.toH2Console()).ignoringRequestMatchers("/public/**"));
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails student = User.builder()
//                .username("student")
//                .password(encoder.encode("student123"))
//                .roles("STUDENT")
//                .build();
//
//        UserDetails guest = User.builder()
//                .username("guest")
//                .password(encoder.encode("guest123"))
//                .roles("GUEST")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, student, guest);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

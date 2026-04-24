
package com.example.security.config;
import com.example.security.auth.JwtAuthenticationFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
 @Bean
 SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwt) throws Exception {
   http.csrf(csrf->csrf.disable())
       .authorizeHttpRequests(auth->auth
         .requestMatchers("/auth/login").permitAll()
         .anyRequest().authenticated())
       .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);
   return http.build();
 }
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c) throws Exception{
   return c.getAuthenticationManager();
 }
}

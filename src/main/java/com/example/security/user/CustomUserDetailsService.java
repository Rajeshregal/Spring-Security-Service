
package com.example.security.user;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository r;
 public CustomUserDetailsService(UserRepository r){this.r=r;}
 public UserDetails loadUserByUsername(String u){
   User user=r.findByUsername(u).orElseThrow();
   var auth=user.roles.stream()
     .map(role->new SimpleGrantedAuthority("ROLE_"+role.roleName)).toList();
   return new org.springframework.security.core.userdetails.User(user.username,user.password,auth);
 }
}

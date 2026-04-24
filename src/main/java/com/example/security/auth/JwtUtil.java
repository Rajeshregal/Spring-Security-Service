
package com.example.security.auth;
import io.jsonwebtoken.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class JwtUtil {
 private final String SECRET="secret";
 public String generateToken(UserDetails ud){
   List<String> roles=ud.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
   return Jwts.builder().setSubject(ud.getUsername()).claim("roles",roles)
     .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+3600000))
     .signWith(SignatureAlgorithm.HS256,SECRET).compact();
 }
 public String extractUsername(String t){
   return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(t).getBody().getSubject();
 }
 public List<String> extractRoles(String t){
   return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(t).getBody().get("roles",List.class);
 }
}

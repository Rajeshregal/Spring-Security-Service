
package com.example.security.auth;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.*;
import javax.crypto.SecretKey;
import java.util.Base64;
@Component
public class JwtUtil {
 private final String SECRET="your-secret-key-must-be-at-least-256-bits-long-for-hs256-algorithm";
 private final SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder().encodeToString(SECRET.getBytes()).getBytes());
 public String generateToken(UserDetails ud){
   List<String> roles=ud.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
   return Jwts.builder().subject(ud.getUsername()).claim("roles",roles)
     .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+3600000))
     .signWith(key).compact();
 }
 @SuppressWarnings("deprecation")
 public String extractUsername(String t){
   return Jwts.parser().setSigningKey(key).build().parseSignedClaims(t).getPayload().getSubject();
 }
 @SuppressWarnings({"deprecation","unchecked"})
 public List<String> extractRoles(String t){
   return (List<String>) Jwts.parser().setSigningKey(key).build().parseSignedClaims(t).getPayload().get("roles",List.class);
 }
}

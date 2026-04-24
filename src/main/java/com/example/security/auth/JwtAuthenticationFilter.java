
package com.example.security.auth;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 private final JwtUtil jwt;
 public JwtAuthenticationFilter(JwtUtil jwt){this.jwt=jwt;}
 protected void doFilterInternal(HttpServletRequest r,HttpServletResponse s,FilterChain c)
   throws ServletException,IOException{
   String h=r.getHeader("Authorization");
   if(h!=null&&h.startsWith("Bearer ")){
     String t=h.substring(7);
     var auth=new UsernamePasswordAuthenticationToken(
       jwt.extractUsername(t),null,
       jwt.extractRoles(t).stream().map(SimpleGrantedAuthority::new).toList());
     SecurityContextHolder.getContext().setAuthentication(auth);
   }
   c.doFilter(r,s);
 }
}

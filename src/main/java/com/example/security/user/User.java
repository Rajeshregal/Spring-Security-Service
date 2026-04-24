
package com.example.security.user;
import com.example.security.role.Role;
import jakarta.persistence.*;
import java.util.Set;
@Entity
@Table(name="users")
public class User {
 @Id @GeneratedValue public Long id;
 public String username;
 public String password;
 @ManyToMany(fetch=FetchType.EAGER) public Set<Role> roles;
}

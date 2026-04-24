
package com.example.security.role;
import jakarta.persistence.*;
@Entity
public class Role {
 @Id @GeneratedValue public Long id;
 @Column(unique=true) public String roleName;
}

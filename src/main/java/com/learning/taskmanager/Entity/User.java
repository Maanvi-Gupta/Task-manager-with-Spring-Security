package com.learning.taskmanager.Entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity  // indicates that this class is a JPA entity and will be mapped to a database table
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER) /*FetchType.LAZY to load roles only when needed and 
                                          FetchType.EAGER to load roles immediately with user*/
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")  // intermediate table to manage the many-to-many relationship 
    )
    private Set<Role> roles;

    public User() {}

    public Long getId() {
        return id;  //primary key 
    }

    public String getUsername() {
        return username;  // candidate key to uniquely identify a user 
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
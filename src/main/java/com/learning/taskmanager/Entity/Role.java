package com.learning.taskmanager.Entity;

import jakarta.persistence.*;  //annotations for JPA

@Entity
@Table(name="roles")  // refers to the table named 'roles' in the database
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment id
    private Long id;

    @Column(nullable = false , unique = true)
    private String name;

    public Role(){}   //empty to load data from database

    public Role(String name) {
        this.name = name;
    }

    public Long getId()
    {
        return id;  //primary key
    }

    public String getName()
    {
        return name;   // candidate key to uniquely identify a role
    }
    
    public String setName(String name)
    {
        this.name = name;
        return name;
    } 
}

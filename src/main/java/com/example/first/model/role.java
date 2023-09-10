package com.example.first.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
//vo role
@Entity
@Data
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<member> users;
}
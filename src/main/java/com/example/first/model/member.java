package com.example.first.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//vo member
@Entity
@Data
public class member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean enable;
    private String Role;
}
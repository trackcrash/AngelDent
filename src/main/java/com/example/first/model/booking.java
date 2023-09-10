package com.example.first.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

//vo booking
@Entity
@Data
public class booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone; ///^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/
    private Date date;
    private String content;
    private boolean active;
}

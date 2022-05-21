package com.example.first.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50, message ="제목은 2자 이상 50자 이하여야 합니다.")
    private String title;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private member user;
}

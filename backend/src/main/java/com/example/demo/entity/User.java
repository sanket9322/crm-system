package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder

@JsonIgnoreProperties({
    "hibernateLazyInitializer",
    "handler"
})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String contact;

    private String city;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
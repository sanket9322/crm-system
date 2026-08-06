package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder

@JsonIgnoreProperties({
    "hibernateLazyInitializer",
    "handler"
})

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contact;

    private String email;

    @Column(length = 2000)
    private String body;

}
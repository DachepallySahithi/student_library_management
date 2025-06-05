package com.example.student_library_management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //marks class as JPA entity
@Table(name = "librarian")
@Data
@AllArgsConstructor //automatically generates a constructor with 1 parameter for each field in class
@NoArgsConstructor //generates constructor with no parameters
@Builder //generates boilerplate code for builder pattern
public class Librarian {
    @Id //marks field as PK of entity
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false) //col in db must not contain null values
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String country;
}

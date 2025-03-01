package com.example.student_library_management_system.model;


import com.example.student_library_management_system.enums.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int pages;

    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Column(name = "is_available", nullable = false)
    private boolean available;

    @JsonBackReference
    @ManyToOne
    @JoinColumn
    private Author author;

    @JsonBackReference
    @ManyToOne
    @JoinColumn
    private Card card;

    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}

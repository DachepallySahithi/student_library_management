package com.example.student_library_management_system.model;

import com.example.student_library_management_system.enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp //automatically adds time & date when card is created
    private Date createDate;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp//automatically updates time & date when card is updated
    private Date updateDate;

    @JsonBackReference
    @OneToOne
    @JoinColumn
    private Student student;

    @JsonManagedReference
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Book> booksIssuedToCard = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction> transactionsForCard = new ArrayList<>();
}

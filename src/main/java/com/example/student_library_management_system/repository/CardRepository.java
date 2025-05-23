package com.example.student_library_management_system.repository;


import com.example.student_library_management_system.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    boolean existsByStudent_Id(int student_id);
    Optional<Card> findByStudent_Id(int student_id);
}

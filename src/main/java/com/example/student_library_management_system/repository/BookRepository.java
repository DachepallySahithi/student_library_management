package com.example.student_library_management_system.repository;


import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    List<Book> findByCard_Id(int card_id);
}

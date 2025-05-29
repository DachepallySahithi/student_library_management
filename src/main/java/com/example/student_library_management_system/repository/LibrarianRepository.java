package com.example.student_library_management_system.repository;

import com.example.student_library_management_system.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    public  Librarian findLibrarianById(int id);
    public  Librarian findLibrarianByEmail(String email);
    public boolean existsByEmail(String email);
}

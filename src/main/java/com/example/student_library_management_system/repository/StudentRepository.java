package com.example.student_library_management_system.repository;

import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    //1. writing own methods with fields / attributes with JPA support
    public Student findByEmail(String email);
    public Student findByName(String student_name);
    public boolean existsByEmail(String email);
    public List<Student> findBySem(String sem);
    public List<Student> findByDepartment(String department);
    public List<Student> findBySemAndDepartment(String sem, String department);

    //2.writing our own complete query without JPA support

    @Query(nativeQuery = true, value = "SELECT * FROM student where sem = :inputSem;")
    public List<Student> getStudentBySem(String inputSem);
}
package com.example.student_library_management_system.service;

import com.example.student_library_management_system.converters.StudentConverter;
import com.example.student_library_management_system.enums.CardStatus;
import com.example.student_library_management_system.model.*;
import com.example.student_library_management_system.repository.AuthorRepository;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<String> studentLogin(StudentRequestDto studentRequestDto){
        if(studentRepository.existsByEmail(studentRequestDto.getEmail())){
            Student student=studentRepository.findByEmail(studentRequestDto.getEmail());
            if(studentRequestDto.getPassword().equals(String.valueOf(student.getId())))
                return ResponseEntity.status(HttpStatus.OK).body("Student Can Login");
            else
                return ResponseEntity.status(HttpStatus.OK).body("Please enter correct Password");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body("Student Does not exists");
        }
    }

    public List<Student> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public long countStudents(){
        long count = studentRepository.count();
        return count;
    }

    public ResponseEntity<String> addStudent(StudentRequestDto studentRequestDto){
        Student student = StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDto);
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setStudent(student);
        student.setCard(card);
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.OK).body("Student Saved Successfully");
    }

    public Student getStudentById(int studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            return student;
        }
        else return new Student();
    }

    public String deleteStudentById(int studentId){
        studentRepository.deleteById(studentId);
        return "Student Deleted Successfully";
    }

    public String updateStudent(int studentId, StudentRequestDto newstudentRequestDto){
        Student student = getStudentById(studentId);
        if(student != null){
            student.setName(newstudentRequestDto.getName());
            student.setGender(newstudentRequestDto.getGender());
            student.setSem(newstudentRequestDto.getSem());
            student.setDob(newstudentRequestDto.getDob());
            student.setEmail(newstudentRequestDto.getEmail());
            student.setDepartment(newstudentRequestDto.getDepartment());
            studentRepository.save(student);
            return "Student Updated Successfully";
        }else{
            return "Cannot find student to update";
        }
    }

    public List<Student> getStudentByDepartment(String department){
        List<Student> studentList = studentRepository.findByDepartment(department);
        return studentList;
    }

    public List<Student> getStudentBySem(String sem){
        List<Student> studentList = studentRepository.findBySem(sem);
        return studentList;
    }

    //Paging & sorting:
    public List<Student> getStudentsBasedOnPage(int pageNo, int pageSize, String sortInput){
        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortInput).ascending()));
        List<Student> studentList = studentPage.getContent();
        return studentList;
    }

    public List<Author> getAuthorsByStudentName(String studentName){
            Student student=studentRepository.findByName(studentName);
            Card card = student.getCard();
            List<Book> books= card.getBooksIssuedToCard();
            List<Author> authors= new ArrayList<>();
            for(int i=0;i<books.size();i++){
                authors.add(books.get(i).getAuthor());
            }
            return authors;
    }
}

package com.example.student_library_management_system.service;

import com.example.student_library_management_system.converters.StudentConverter;
import com.example.student_library_management_system.enums.CardStatus;
import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.repository.AuthorRepository;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(StudentRequestDto studentRequestDto){
        Student student = StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDto);

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setStudent(student);
        student.setCard(card);
        studentRepository.save(student);
        return "Student Saved Successfully";
    }

    public Student getStudentById(int studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            return student;
        }
        else return new Student();
    }

    public List<Student> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    //Paging & sorting:

    public List<Student> getStudentsBasedOnPage(int pageNo, int pageSize, String sortInput){

        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortInput).ascending()));
        List<Student> studentList = studentPage.getContent();
        return studentList;
    }


    public long countStudents(){
        long count = studentRepository.count();
        return count;
    }

    public String deleteStudentById(int studentId){
        studentRepository.deleteById(studentId);
        return "student deleted";
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
            return "student updated successfully";
        }else{
            return "Cannot find student to update";
        }
    }

    public List<Student> getStudentByDepartment(String department){
        List<Student> studentList = studentRepository.findByDepartment(department);
        return studentList;
    }

    public List<Student> getStudentBySem(String sem){
        List<Student> studentList = studentRepository.findByDepartment(sem);
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

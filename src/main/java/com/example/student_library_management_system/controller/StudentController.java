package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import com.example.student_library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/apis")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.addStudent(studentRequestDto);
        return response;
    }

    @GetMapping("/find{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        Student student = studentService.getStudentById(studentId);
        return student;
    }

    @GetMapping("/findAll")
    public List<Student> getAllStudents(){
        List<Student> studentList = studentService.getAllStudents();
        return studentList;
    }

    @GetMapping("/findByPage")
    public List<Student> getStudentsByPages(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortInput){
        List<Student> studentList = studentService.getStudentsBasedOnPage(pageNo, pageSize, sortInput);
        return studentList;
    }

    @GetMapping("/count")
    public String countStudents(){
       long count = studentService.countStudents();
       return "Total students present in database are :" +count;
    }

    @DeleteMapping("/delete{studentId}")
    public String deleteStudentById(@PathVariable int studentId){
        String response = studentService.deleteStudentById(studentId);
        return response;
    }

    @PutMapping("/update/{studentId}")
    public String updateStudent(@PathVariable int studentId, @RequestBody StudentRequestDto studentRequestDto){
        String response = studentService.updateStudent(studentId,studentRequestDto);
        return response;
    }

    @GetMapping("/findByDept")
    public List<Student> getStudentByDepartment(@RequestParam String department){
        List<Student> studentList = studentService.getStudentByDepartment(department);
        return studentList;
    }

    @GetMapping("/findBySem")
    public List<Student> getStudentBySem(@RequestParam String sem){
        List<Student> studentList = studentService.getStudentBySem(sem);
        return studentList;
    }

//    @GetMapping("/get-list-of-authors")
//    public List<Author> getListOfAuthors(@RequestParam String student_name) {
//        return studentService.getAuthorsByStudentName(student_name);
//    }
}

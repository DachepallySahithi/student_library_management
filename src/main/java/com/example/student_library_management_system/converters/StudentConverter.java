package com.example.student_library_management_system.converters;


import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;

public class StudentConverter {

    //converts studentRequestDto into student class


    public static Student convertStudentRequestDtoIntoStudent(StudentRequestDto studentRequestDto){
        Student student = Student.builder().name(studentRequestDto.getName())
                .email(studentRequestDto.getEmail())
                .dob(studentRequestDto.getDob())
                .department(studentRequestDto.getDepartment())
                .gender(studentRequestDto.getGender())
                .sem(studentRequestDto.getSem())
                .build();
        return student;
    }
}

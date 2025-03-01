package com.example.student_library_management_system.requestdto;

import lombok.Data;

@Data
public class AuthorRequestDto {

    private String name;
    private String gender;
    private String country;
    private double rating;
}

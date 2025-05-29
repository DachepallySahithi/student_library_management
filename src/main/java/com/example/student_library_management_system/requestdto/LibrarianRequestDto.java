package com.example.student_library_management_system.requestdto;

import lombok.Data;

@Data
public class LibrarianRequestDto {
    private String name;
    private String email;
    private String gender;
    private String country;
    private String password;
}

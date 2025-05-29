package com.example.student_library_management_system.converters;

import com.example.student_library_management_system.model.Librarian;
import com.example.student_library_management_system.requestdto.LibrarianRequestDto;

public class LibrarianConverter {
    public static Librarian convertLibrarianRequestDtoIntoLibrarian(LibrarianRequestDto librarianRequestDto){
        Librarian librarian = Librarian.builder()
                .name(librarianRequestDto.getName())
                .gender(librarianRequestDto.getGender())
                .country(librarianRequestDto.getCountry())
                .email(librarianRequestDto.getEmail())
                .password(librarianRequestDto.getPassword())
                .build();
        return librarian;
    }
}

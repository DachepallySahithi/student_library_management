package com.example.student_library_management_system.converters;

import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.requestdto.AuthorRequestDto;

public class AuthorConverter {

    public static Author convertAuthorRequestDtoIntoAuthor(AuthorRequestDto authorRequestDto){
        Author author = Author.builder()
                .name(authorRequestDto.getName())
                .gender(authorRequestDto.getGender())
                .country(authorRequestDto.getCountry())
                .rating(authorRequestDto.getRating())
                .build();
        return author;
    }
}
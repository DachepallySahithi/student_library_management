package com.example.student_library_management_system.requestdto;

import com.example.student_library_management_system.enums.Genre;
import lombok.Data;

@Data
public class BookRequestDto {

    private String name;
    private int pages;
    private String publisherName;
    private Genre genre;
    private boolean available;
    private int authorId;
    private int cardId;
}

package com.example.student_library_management_system.converters;

import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.requestdto.BookRequestDto;

public class BookConverter {

    public static Book convertBookRequestDtoIntoBook(BookRequestDto bookRequestDto){
        Book book = Book.builder()
                .name(bookRequestDto.getName())
                .pages(bookRequestDto.getPages())
                .publisherName(bookRequestDto.getPublisherName())
                .genre(bookRequestDto.getGenre())
                .available(bookRequestDto.isAvailable())
                .build();
        return book;
    }
}

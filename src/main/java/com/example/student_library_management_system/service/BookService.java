package com.example.student_library_management_system.service;

import com.example.student_library_management_system.converters.BookConverter;
import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.repository.AuthorRepository;
import com.example.student_library_management_system.repository.BookRepository;
import com.example.student_library_management_system.repository.CardRepository;
import com.example.student_library_management_system.requestdto.BookRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(BookRequestDto bookRequestDto){

        Book book = BookConverter.convertBookRequestDtoIntoBook(bookRequestDto);

        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        book.setAuthor(author);

        Card card = cardRepository.findById(bookRequestDto.getCardId()).get();
        book.setCard(card);

        bookRepository.save(book);
        return "Book Saved into database";
    }
}

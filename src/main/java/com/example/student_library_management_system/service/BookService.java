package com.example.student_library_management_system.service;

import com.example.student_library_management_system.converters.BookConverter;
import com.example.student_library_management_system.enums.CardStatus;
import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.repository.AuthorRepository;
import com.example.student_library_management_system.repository.BookRepository;
import com.example.student_library_management_system.repository.CardRepository;
import com.example.student_library_management_system.requestdto.BookRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public ResponseEntity<String> addBook(BookRequestDto bookRequestDto){
        Book book = BookConverter.convertBookRequestDtoIntoBook(bookRequestDto);

        //from authorId we get whole author details
        if(authorRepository.existsById(bookRequestDto.getAuthorId())){
            Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
            book.setAuthor(author);
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body("Author Does not exists");
        //from cardId we get card details
        Optional<Card> card = cardRepository.findById(bookRequestDto.getCardId());
        if(card.isPresent()){ //cardId = 0 indicates this book is not assigned to any student
            if(card.get().getCardStatus().equals(CardStatus.ACTIVE)){
                book.setCard(card.get());
                bookRepository.save(book);
                return ResponseEntity.status(HttpStatus.OK).body("Book Saved into database");
            }
            else{
                return ResponseEntity.status(HttpStatus.OK).body("Your card is "+card.get().getCardStatus()+" So we cannot Allocate this Book");
            }
        }
        else if(bookRequestDto.getCardId() == 0){
            bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.OK).body("Book Saved into database");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body("Card Does not exists");
        }
    }

    public ResponseEntity<String> updateBook(int bookId, BookRequestDto bookRequestDto) {
        if(bookRepository.existsById(bookId)){
            Optional<Card> card= cardRepository.findById(bookRequestDto.getCardId());
            Book book = bookRepository.getById(bookId);
            book.setName(bookRequestDto.getName());
            book.setPages(bookRequestDto.getPages());
            book.setPublisherName(bookRequestDto.getPublisherName());
            book.setGenre(bookRequestDto.getGenre());
            book.setAvailable(bookRequestDto.isAvailable()?1:0);
            String status = "Book Updated Successfully";
            if(card.isPresent())
                book.setCard(card.get());
            else if(bookRequestDto.getCardId()==0){
                status="Book Unallocated Successfully";
                book.setCard(null);
            }
            else
                status="Card is not Found";
            bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body("Book not found");
        }
    }
    //delete
    public ResponseEntity<String> deleteBookById(int bookId){
        if(bookRepository.existsById(bookId)){
            bookRepository.deleteById(bookId);
            return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("Book does not exists");
        }
    }
}

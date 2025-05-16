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

    public String addBook(BookRequestDto bookRequestDto){

        Book book = BookConverter.convertBookRequestDtoIntoBook(bookRequestDto);

        //from authorId we get whole author details
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        book.setAuthor(author);

        //from cardId we get card details
        Optional<Card> card = cardRepository.findById(bookRequestDto.getCardId());
        if(card.isPresent()){
            if(card.get().getCardStatus().equals(CardStatus.ACTIVE)){
                book.setCard(card.get());
                bookRepository.save(book);
                return "Book Saved into database";
            }
            else{
                return "Your card is "+card.get().getCardStatus()+" So we cannot Allocate this Book";
            }
        }else{
            return "Card Does not exists";
        }
    }


    public String updateBook(int bookId, BookRequestDto bookRequestDto) {
        if(bookRepository.existsById(bookId)){
            Book book = bookRepository.getById(bookId);
            book.setName(bookRequestDto.getName());
            book.setPages(bookRequestDto.getPages());
            book.setPublisherName(bookRequestDto.getPublisherName());
            book.setGenre(bookRequestDto.getGenre());
            book.setAvailable(bookRequestDto.isAvailable());
            bookRepository.save(book);
            return "Book updated successfully";
        }
        else{
            return "Book not found";
        }
    }
    //delete
    public String deleteBookById(int bookId){
        if(bookRepository.existsById(bookId)){
            bookRepository.deleteById(bookId);
            return "book deleted";
        }
        else return "book does not exists";
    }
}

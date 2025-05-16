package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.requestdto.BookRequestDto;
import com.example.student_library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/apis")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public String saveBook(@RequestBody BookRequestDto bookRequestDto){
        String response = bookService.addBook(bookRequestDto);
        return response;
    }

    @PutMapping("/update/{bookId}")
    public String updateBook(@PathVariable int bookId, @RequestBody BookRequestDto bookRequestDto){
        String response = bookService.updateBook(bookId, bookRequestDto);
        return response;
    }

    @DeleteMapping("/delete/{bookId}")
    public String deleteBookById(@PathVariable int bookId){
        String response = bookService.deleteBookById(bookId);
        return response;
    }
}

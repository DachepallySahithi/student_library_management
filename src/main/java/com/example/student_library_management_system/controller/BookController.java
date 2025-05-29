package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.requestdto.BookRequestDto;
import com.example.student_library_management_system.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/book/apis")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<String> saveBook(@RequestBody BookRequestDto bookRequestDto){
        ResponseEntity<String> responseEntity= bookService.addBook(bookRequestDto);
        return responseEntity;
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable int bookId, @RequestBody BookRequestDto bookRequestDto){
        ResponseEntity<String> responseEntity= bookService.updateBook(bookId, bookRequestDto);
        return responseEntity;
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable int bookId){
        ResponseEntity<String> responseEntity = bookService.deleteBookById(bookId);
        return responseEntity;
    }
}

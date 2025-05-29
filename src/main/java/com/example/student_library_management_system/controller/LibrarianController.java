package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.requestdto.LibrarianRequestDto;
import com.example.student_library_management_system.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/librarian/apis")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PostMapping("/create")
    public ResponseEntity createLibrarian(@RequestBody LibrarianRequestDto librarianRequestDto){
        ResponseEntity response = librarianService.addLibrarian(librarianRequestDto);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity librarianLogin(@RequestBody LibrarianRequestDto librarianRequestDto){
        ResponseEntity response=librarianService.librarianLogin(librarianRequestDto);
        return response;
    }
}

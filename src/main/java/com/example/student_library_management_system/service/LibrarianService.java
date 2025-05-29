package com.example.student_library_management_system.service;

import com.example.student_library_management_system.converters.LibrarianConverter;
import com.example.student_library_management_system.model.Librarian;
import com.example.student_library_management_system.repository.LibrarianRepository;
import com.example.student_library_management_system.requestdto.LibrarianRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService {
    @Autowired
    LibrarianRepository librarianRepository;

    public ResponseEntity<String> addLibrarian(LibrarianRequestDto librarianRequestDto){
        if(librarianRepository.existsByEmail(librarianRequestDto.getEmail()))
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Librarian Added Successfully");
        else{
            Librarian librarian= LibrarianConverter.convertLibrarianRequestDtoIntoLibrarian(librarianRequestDto);
            librarianRepository.save(librarian);
            return ResponseEntity.status(HttpStatus.OK).body("Librarian Added Successfully");
        }
    }

    public ResponseEntity<String> librarianLogin(LibrarianRequestDto librarianRequestDto){
        if(librarianRepository.existsByEmail(librarianRequestDto.getEmail())){
            Librarian librarian=librarianRepository.findLibrarianByEmail(librarianRequestDto.getEmail());
            if(librarianRequestDto.getPassword().equals(librarian.getPassword()))
                return ResponseEntity.status(HttpStatus.OK).body("Librarian Can Login");
            else
                return ResponseEntity.status(HttpStatus.OK).body("Please enter correct Password");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body("Librarian Does not exists");
        }
    }
}

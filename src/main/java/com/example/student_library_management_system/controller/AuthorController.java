package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.requestdto.AuthorRequestDto;
import com.example.student_library_management_system.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/author/apis")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/save")
    public String saveAuthor(@RequestBody AuthorRequestDto authorRequestDto){
     String response = authorService.addAuthor(authorRequestDto);
     return response;
    }

    @PutMapping("/update-author")
    public String updateAuthor(@RequestParam int authorId, @RequestBody AuthorRequestDto authorRequestDto){
        return authorService.updateAuthor(authorId,authorRequestDto);
    }

    /*@PatchMapping("/update-author")
    public String updateAuthor(@RequestParam int authorId, @RequestBody Map<String, Object> updateMap){
        return authorService.updateAuthor(authorId,updateMap);
    }*/

    @DeleteMapping("/delete-author")
    public String deleteAuthor(@RequestParam int authorId){
        return authorService.deleteAuthor(authorId);
    }
}
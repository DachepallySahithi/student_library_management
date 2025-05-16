package com.example.student_library_management_system.service;

import com.example.student_library_management_system.converters.AuthorConverter;
import com.example.student_library_management_system.model.Author;
import com.example.student_library_management_system.repository.AuthorRepository;
import com.example.student_library_management_system.requestdto.AuthorRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(AuthorRequestDto authorRequestDto){
        Author author = AuthorConverter.convertAuthorRequestDtoIntoAuthor(authorRequestDto);
        authorRepository.save(author);
        return "Author Saved Successfully";
    }

    //update

    public String updateAuthor(int id, Map<String, Object> authorRequestMap){
        try{
            Author author = authorRepository.findAuthorById(id);
            /*int id, @AuthorRequestDTO authorRequestDTO
            author.setName(authorRequestDto.getName());
            author.setGender(authorRequestDto.getGender());
            author.setCountry(authorRequestDto.getCountry());
            author.setRating(authorRequestDto.getRating());*/
            if(authorRequestMap.containsKey("name"))author.setName(authorRequestMap.get("name").toString());
            if(authorRequestMap.containsKey("country"))author.setCountry(authorRequestMap.get("country").toString());
            if(authorRequestMap.containsKey("gender"))author.setGender(authorRequestMap.get("gender").toString());
            if(authorRequestMap.containsKey("rating"))author.setRating((Double)authorRequestMap.get("rating"));
            authorRepository.save(author);
            return "Author Updated Successfully";
        }
        catch(Exception e){
            return "Author Update Failed. Pls Try After Sometime...";
        }
    }

    //delete
    @Transactional
    public String deleteAuthor(int authorId){
        if(authorRepository.existsById(authorId)) {
            authorRepository.deleteById(authorId);
            return "Author Deleted Successfully";
        }
        else return "Author Id Does not exist";
    }
}
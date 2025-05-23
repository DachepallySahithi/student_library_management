package com.example.student_library_management_system.controller;


import com.example.student_library_management_system.enums.CardStatus;
import com.example.student_library_management_system.requestdto.CardRequestDto;
import com.example.student_library_management_system.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card/apis")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/save")
    public String saveCard(@RequestBody CardRequestDto cardRequestDto){
        String response = cardService.addCard(cardRequestDto);
        return response;
    }

    @PutMapping("/update/{cardId}")
    public String updateCard(@PathVariable int cardId, @RequestBody CardRequestDto cardRequestDto){
        String response = cardService.updateCard(cardId, cardRequestDto);
        return response;
    }

    @DeleteMapping("/delete/{cardId}")
    public String deleteCard(@PathVariable int cardId){
        return cardService.deleteCard(cardId);
    }

    @GetMapping("/print")
    public ResponseEntity printCard(@RequestParam int studentId){
        return cardService.printCard(studentId);
    }
}

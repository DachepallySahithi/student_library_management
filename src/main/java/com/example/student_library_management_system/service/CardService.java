package com.example.student_library_management_system.service;

import com.example.student_library_management_system.converters.CardConverter;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.repository.CardRepository;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.CardRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    StudentRepository studentRepository;

    //add
    public String addCard(CardRequestDto cardRequestDto){
        Optional<Student> student = studentRepository.findById(cardRequestDto.getStudentId());
        if(student.isPresent()){
            if(cardRepository.existsByStudent_Id(student.get().getId())){
                return "Card Already Alloted to "+student.get().getName();
            }
            else{
                Card card = CardConverter.convertCardRequestDtoIntoCard(cardRequestDto,student.get());
                cardRepository.save(card);
                return "Card Alloted to "+student.get().getName();
            }
        }
        else{
            return "Student Does not exist";
        }
    }

    //update
    public String updateCard(int cardId, CardRequestDto cardRequestDto){
        //Card card = CardRequestDto.findById(cardId);
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if(optionalCard.isPresent()){
            Card card = optionalCard.get();
            card.setCardStatus(cardRequestDto.getCardStatus());
            cardRepository.save(card);
            return "Card updated successfully";
        }
        else{
            return "Card not found";
        }
    }

    //delete
    public String deleteCard(int cardId){
        if(cardRepository.existsById(cardId)){
            cardRepository.deleteById(cardId);
            return "Card deleted successfully";
        }
        else{
            return "Card Id not found";
        }
    }
    //print
}

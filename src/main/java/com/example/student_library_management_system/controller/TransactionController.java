package com.example.student_library_management_system.controller;

import com.example.student_library_management_system.requestdto.TransactionRequestDto;
import com.example.student_library_management_system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transaction/apis")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<String> saveTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
        ResponseEntity<String> response = transactionService.addTransaction(transactionRequestDto);
        return response;
    }
}

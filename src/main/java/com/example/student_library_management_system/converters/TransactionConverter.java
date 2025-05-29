package com.example.student_library_management_system.converters;


import com.example.student_library_management_system.model.Transaction;
import com.example.student_library_management_system.requestdto.TransactionRequestDto;

public class TransactionConverter {
    public static Transaction convertTransactionRequestDtoIntoTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = Transaction.builder()
                .transactionStatus(transactionRequestDto.getTransactionStatus())
                .fine(transactionRequestDto.getFine())
                .issueOrReturn(transactionRequestDto.getIssueOrReturn())
                .dueDate(transactionRequestDto.getDueDate())
                .build();
        return transaction;
    }
}

package com.example.student_library_management_system.requestdto;

import com.example.student_library_management_system.enums.TransactionStatus;
import lombok.Data;


@Data
public class TransactionRequestDto {
    private int cardId;
    private int bookId;
    private String dueDate;
    private TransactionStatus transactionStatus;
    private double fine;
    private String issueOrReturn;
}

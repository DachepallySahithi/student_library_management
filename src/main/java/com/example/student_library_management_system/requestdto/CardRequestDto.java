package com.example.student_library_management_system.requestdto;

import com.example.student_library_management_system.enums.CardStatus;
import lombok.Data;

@Data
public class CardRequestDto {

    private CardStatus cardStatus;
    private int studentId;
}

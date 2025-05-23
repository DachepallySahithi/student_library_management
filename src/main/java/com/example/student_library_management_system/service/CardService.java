package com.example.student_library_management_system.service;

import com.example.student_library_management_system.converters.CardConverter;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.repository.BookRepository;
import com.example.student_library_management_system.repository.CardRepository;
import com.example.student_library_management_system.repository.StudentRepository;
import com.example.student_library_management_system.requestdto.CardRequestDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

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
    public ResponseEntity printCard(int studentId){ //RE -> class contains whole http response(status code, header, body)
        Optional<Student> student = studentRepository.findById(studentId); //getting student details from student table using studentId
        Optional<Card> card = cardRepository.findByStudent_Id(studentId);// getting card details from card table using studentId
        if(student.isPresent()) {
            if(card.isPresent()){
                ByteArrayInputStream pdfStream = createPdf(student.get(),card.get());
                byte[] pdfBytes = pdfStream.readAllBytes();

                //setting header response
                String filename=student.get().getName()+" Library Card.pdf";
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment; filename="+filename);
                headers.add("X-Message", "Card Downloaded Successfully");

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdfBytes);
            }
            else{
                HttpHeaders headers = new HttpHeaders();
                headers.add("X-Message", "Card is not allocated to Student");

                return ResponseEntity.notFound()
                        .headers(headers)
                        .build();
            }
        }
        else{
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Message", "Please give valid Student Id");

            return ResponseEntity.notFound()
                    .headers(headers)
                    .build();
        }
    }

    public ByteArrayInputStream createPdf(Student student, Card card) { //ByteArrayInputStream read data from a byte array in memory (contains the generated PDF's bytes
        Document document = new Document(); //refers to pdf file
        ByteArrayOutputStream out = new ByteArrayOutputStream();//Writes data into a byte array in memory

        try {
            PdfWriter.getInstance(document, out); // Connects the Document to the ByteArrayOutputStream for writing.
            document.open(); // opens doc for writing content

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph para = new Paragraph("LIBRARY CARD", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);  //adds new line

            // Student & Card table
            PdfPTable detailsTable = new PdfPTable(2);  //PdfPTable is used for creating table
            detailsTable.setWidthPercentage(100);
            detailsTable.addCell("Student ID: "+student.getId()); //addCell ->  adds a cell (box) in the table
            detailsTable.addCell("Card Id: "+card.getId());
            detailsTable.addCell("Name: "+student.getName());
            detailsTable.addCell("Email: "+student.getEmail());
            detailsTable.addCell("Date of Birth: "+student.getDob());
            detailsTable.addCell("Gender: "+student.getGender());
            detailsTable.addCell("Department: "+student.getDepartment());
            detailsTable.addCell("Semester: "+student.getSem());

            // book table
            PdfPTable booksTable = new PdfPTable(8);
            booksTable.setWidthPercentage(100);
            booksTable.addCell("S.NO ");
            booksTable.addCell("Book Name ");
            booksTable.addCell("Book Id ");
            booksTable.addCell("Genre ");
            booksTable.addCell("Taken Date ");
            booksTable.addCell("Submission Date ");
            booksTable.addCell("Remarks ");
            booksTable.addCell("Signature ");

            //books associated with cardId
            List<Book> bookList = bookRepository.findByCard_Id(card.getId());
            int sno=1;
            for(Book book: bookList){
                booksTable.addCell(String.valueOf(sno));
                booksTable.addCell(book.getName());
                booksTable.addCell(String.valueOf(book.getId()));
                booksTable.addCell(book.getGenre().toString());
                booksTable.addCell("     ");
                booksTable.addCell("     ");
                booksTable.addCell("     ");
                booksTable.addCell("     ");
                sno++;
            }

            document.add(detailsTable); //Adds the student & card details table
            document.add(new Paragraph("\n\n")); //2 lines space b/w tables
            document.add(booksTable);//adds book table
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
        // converts the currently stored data in the ByteArrayOutputStream into a byte array (byte[])
    }
}

//addCell -> block or each box
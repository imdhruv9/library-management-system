package com.libraryManagement.libraryManagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@Data
@AllArgsConstructor


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveBookTransactionDto {
    private Long transactionId;
    private Long bookId;
    private String bookName;
    private Long userId;
    private Long librarianId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate submitDate;


}



package com.libraryManagement.libraryManagement.model;


import lombok.Data;

    @Data
    public class BookIssueTransactionDTO {
        private Long bookId;
        private Long userId;
        private Long librarianId;
        private Integer quantity;

    }



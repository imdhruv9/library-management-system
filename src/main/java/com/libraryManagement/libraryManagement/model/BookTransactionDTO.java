package com.libraryManagement.libraryManagement.model;


import lombok.Data;

    @Data
    public class BookTransactionDTO {
        private Long bookId;
        private Long userId;
        private Long librarianId;


    }



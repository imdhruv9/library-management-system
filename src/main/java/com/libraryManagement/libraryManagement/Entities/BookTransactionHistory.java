package com.libraryManagement.libraryManagement.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

    @Data

    @Entity
    @NoArgsConstructor
    @AllArgsConstructor

    public class BookTransactionHistory {
        @Id
        @Column(name = "transaction_id")
        private Long transactionId;

        @ManyToOne
        @JoinColumn(name = "book_id", nullable = false)
        private BookInventory bookId;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private Users userId;

        @ManyToOne
        @JoinColumn(name = "librarian_Id", nullable = false)
        private Librarian librarianId;


        private LocalDate issuedDate;

        private LocalDate dueDate;
        private LocalDate submitDate;
        public BookTransactionHistory(BookTransactionEntity entity) {
            this.transactionId = entity.getTransactionId();
            this.bookId = entity.getBookId();
            this.userId = entity.getUserId();
            this.librarianId = entity.getLibrarianId();
            this.issuedDate = entity.getIssuedDate();
            this.dueDate = entity.getDueDate();
            this.submitDate = entity.getSubmitDate();
        }


    }











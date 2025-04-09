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
// applying a constraints so that no user can take same book again
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "user_id"}))
public class BookTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
   public BookTransactionEntity
           (BookInventory bookId,Users userId,Librarian librarianId){
       this.bookId= bookId;
       this.userId = userId;
       this.librarianId = librarianId;
       this.issuedDate = LocalDate.now();
       this.dueDate = calculateReturnDate(bookId.getMaxRentalDays(), issuedDate);


   }



    private LocalDate calculateReturnDate(Integer maxRentalDays,LocalDate issuedDate){
       return issuedDate.plusDays(maxRentalDays);
   }



}

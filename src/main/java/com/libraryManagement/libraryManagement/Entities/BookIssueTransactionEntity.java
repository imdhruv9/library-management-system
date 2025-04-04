package com.libraryManagement.libraryManagement.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data

@Entity
@NoArgsConstructor
// applying a constraints so that no user can take same book again
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "user_id"}))
public class BookIssueTransactionEntity {
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


    private Integer quantity;

    private LocalDate issuedDate;

    private LocalDate returnDate;
   public BookIssueTransactionEntity
           (BookInventory bookId,Users userId,Librarian librarianId,Integer quantity){
       this.bookId= bookId;
       this.userId = userId;
       this.librarianId = librarianId;
       this.quantity = quantity;
       this.issuedDate = LocalDate.now();
       this.returnDate = calculateReturnDate(bookId.getMaxRentalDays(), issuedDate);

   }
   private LocalDate calculateReturnDate(Integer maxRentalDays,LocalDate issuedDate){
       return issuedDate.plusDays(maxRentalDays);
   }



}

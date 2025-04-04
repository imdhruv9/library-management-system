package com.libraryManagement.libraryManagement.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "book_inventory")
@Data
public class BookInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author_name")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "max_rental_days")
    private Integer maxRentalDays;

    @Column(name = "price")
    private Integer price;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    @Column(name = "librarian_id")
    private Integer librarianId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "is_active")
    private Boolean isActive;


}

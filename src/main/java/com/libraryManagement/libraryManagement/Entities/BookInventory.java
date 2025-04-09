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

    @NotNull
    @Column(name = "book_name" ,unique = true ,nullable = false)
    private String bookName;


    @Column(name = "author_name"  ,nullable = false)
    private String author;

    @Column(name = "category"  ,nullable = false)
    private String category;

    @Column(name = "max_rental_days"  ,nullable = false)
    private Integer maxRentalDays;

    @Column(name = "price"  ,nullable = false)
    private Integer price;

    @Column(name = "total_quantity"  ,nullable = false)
    private Integer totalQuantity;

    @Column(name = "available_quantity",nullable = false)
    private Integer availableQuantity;

    @Column(name = "librarian_id" ,nullable = false)
    private Integer librarianId;

    @Column(name = "created_date"  ,nullable = false)
    private Date createdDate;

    @Column(name = "is_active" ,nullable = false)
    private Boolean isActive;


}

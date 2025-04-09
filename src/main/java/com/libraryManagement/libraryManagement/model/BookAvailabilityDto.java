package com.libraryManagement.libraryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookAvailabilityDto {
    private Long bookId;
    private String BookName;
    private String author;
    private String category;
    private Integer price;
    private  Integer availableQuantity;

}

package com.libraryManagement.libraryManagement.model;

import lombok.Data;

@Data
public class UpdateBookQuantity {
    private Long bookId;
    private Integer quantity;

}

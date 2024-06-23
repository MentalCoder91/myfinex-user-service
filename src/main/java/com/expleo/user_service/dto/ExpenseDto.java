package com.expleo.user_service.dto;


import lombok.Data;


@Data
public class ExpenseDto {

    private Long id;


    private Long userId;


    private Long categoryId;

    private String categoryName;

    private Double amount;


    private String description;

}
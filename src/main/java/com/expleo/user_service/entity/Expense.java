package com.expleo.user_service.entity;


import lombok.Data;



@Data
public class Expense {

    private Long id;


    private Long userId;


    private Long categoryId;


    private Double amount;


    private String description;

}
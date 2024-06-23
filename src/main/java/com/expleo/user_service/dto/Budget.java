package com.expleo.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    private Long id;
    private Long userId;
    private Double amount;
    private BudgetCategory category;
    private Object message;


}

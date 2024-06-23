package com.expleo.user_service.service.client;


import com.expleo.user_service.entity.Expense;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "EXPENSE-SERVICE",fallbackFactory = ExpenseClientFallbackFactory.class)
public interface ExpenseClient {

    @GetMapping("/expense/{userId}")
    List<Expense> getExpensesForUsers(@RequestParam("userId") Long userId);
}
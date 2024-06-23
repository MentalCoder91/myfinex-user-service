package com.expleo.user_service.service.client;


import com.expleo.user_service.dto.Budget;
import com.expleo.user_service.dto.BudgetCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "BUDGET-SERVICE", fallbackFactory = BudgetClientFallbackFactory.class)
public interface BudgetClient {

    @GetMapping("/budget/categories")
    List<BudgetCategory> getAllCategories();
}


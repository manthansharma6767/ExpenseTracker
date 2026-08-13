package com.expense.dto;

import com.expense.model.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ExpenseResponse {

    private Long expenseId;
    private Double amount;
    private Category category;
    private String description;
    private LocalDate date;
    private Long studentId;
    private String studentName;

}
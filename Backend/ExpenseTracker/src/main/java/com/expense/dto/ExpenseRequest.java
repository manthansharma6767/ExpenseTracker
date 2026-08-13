package com.expense.dto;

import com.expense.model.Category;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseRequest {

    private Double amount;
    private Category category;
    private String description;
    private LocalDate date;
    private Long studentId;

}

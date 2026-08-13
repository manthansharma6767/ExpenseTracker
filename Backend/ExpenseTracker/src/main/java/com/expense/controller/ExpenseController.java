package com.expense.controller;

import com.expense.dto.ExpenseRequest;
import com.expense.dto.ExpenseResponse;
import com.expense.dto.StudentResponse;
import com.expense.model.Category;
import com.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ExpenseResponse createExpense(@RequestBody ExpenseRequest expenseRequest) {
        return expenseService.createExpense(expenseRequest);
    }

    @GetMapping
    public List<ExpenseResponse> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // GET BY STUDENT
    @GetMapping("/student/{studentId}")
    public List<ExpenseResponse> getByStudent(@PathVariable Long studentId) {
        return expenseService.getByStudent(studentId);
    }

    @GetMapping("student/{studentId}/category/{categoryId}")
    public List<ExpenseResponse> getByCateGory(@PathVariable Long studentId , @PathVariable Category category) {
        return expenseService.getByCategory(studentId , category);
    }

    @PutMapping("/{id}")
    public ExpenseResponse updateExpense(@PathVariable Long id , @RequestBody ExpenseRequest request) {
        return expenseService.updateExpense(id , request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }

    @GetMapping("/student/{studentId}/date")
    public List<ExpenseResponse> getByDateRange(
            @PathVariable Long studentId,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        return expenseService.getByDateRange(studentId, start, end);
    }

}

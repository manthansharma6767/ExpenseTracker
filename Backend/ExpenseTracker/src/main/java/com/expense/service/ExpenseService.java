package com.expense.service;

import com.expense.dto.ExpenseRequest;
import com.expense.dto.ExpenseResponse;
import com.expense.model.Category;
import com.expense.model.Expense;
import com.expense.model.Student;
import com.expense.repository.ExpenseRepository;
import com.expense.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final StudentRepository studentRepository;

    // CREATE
    public ExpenseResponse createExpense(ExpenseRequest request) {

        Student student = studentRepository.findById(request.getStudentId()).orElse(null);
        if (student == null) throw new RuntimeException("Student Not Found");

        Expense expense = Expense.builder()
                .amount(request.getAmount())
                .category(request.getCategory())
                .description(request.getDescription())
                .date(request.getDate())
                .student(student)
                .build();

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseResponse.builder()
                .expenseId(savedExpense.getExpenseId())
                .amount(savedExpense.getAmount())
                .category(savedExpense.getCategory())
                .description(savedExpense.getDescription())
                .date(savedExpense.getDate())
                .studentId(savedExpense.getStudent().getStudentId())
                .studentName(savedExpense.getStudent().getName())
                .build();
    }

    // GET ALL
    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(e -> ExpenseResponse.builder()
                        .expenseId(e.getExpenseId())
                        .amount(e.getAmount())
                        .category(e.getCategory())
                        .description(e.getDescription())
                        .date(e.getDate())
                        .studentId(e.getStudent().getStudentId())
                        .studentName(e.getStudent().getName())
                        .build())
                .toList();
    }

    public List<ExpenseResponse> getByStudent(Long studentId) {

        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) throw new RuntimeException("Student Not Found");

        return expenseRepository.findByStudent(student)
                .stream()
                .map(e -> ExpenseResponse.builder()
                        .expenseId(e.getExpenseId())
                        .amount(e.getAmount())
                        .category(e.getCategory())
                        .description(e.getDescription())
                        .date(e.getDate())
                        .studentId(e.getStudent().getStudentId())
                        .studentName(e.getStudent().getName())
                        .build())
                .toList();
    }

    // FILTER BY CATEGORY
    public List<ExpenseResponse> getByCategory(Long studentId, Category category) {

        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) throw new RuntimeException("Student not found");

        return expenseRepository.findByStudentAndCategory(student, category)
                .stream()
                .map(e -> ExpenseResponse.builder()
                        .expenseId(e.getExpenseId())
                        .amount(e.getAmount())
                        .category(e.getCategory())
                        .description(e.getDescription())
                        .date(e.getDate())
                        .studentId(e.getStudent().getStudentId())
                        .studentName(e.getStudent().getName())
                        .build())
                .toList();
    }

    // FILTER BY DATE
    public List<ExpenseResponse> getByDateRange(Long studentId, LocalDate start, LocalDate end) {

        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) throw new RuntimeException("Student not found");

        return expenseRepository.findByStudentAndDateBetween(student, start, end)
                .stream()
                .map(e -> ExpenseResponse.builder()
                        .expenseId(e.getExpenseId())
                        .amount(e.getAmount())
                        .category(e.getCategory())
                        .description(e.getDescription())
                        .date(e.getDate())
                        .studentId(e.getStudent().getStudentId())
                        .studentName(e.getStudent().getName())
                        .build())
                .toList();
    }

    // UPDATE
    public ExpenseResponse updateExpense(Long id, ExpenseRequest request) {

        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense == null) throw new RuntimeException("Expense not found");

        Student student = studentRepository.findById(request.getStudentId()).orElse(null);
        if (student == null) throw new RuntimeException("Student not found");

        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDescription(request.getDescription());
        expense.setDate(request.getDate());
        expense.setStudent(student);

        Expense updated = expenseRepository.save(expense);

        return ExpenseResponse.builder()
                .expenseId(updated.getExpenseId())
                .amount(updated.getAmount())
                .category(updated.getCategory())
                .description(updated.getDescription())
                .date(updated.getDate())
                .studentId(updated.getStudent().getStudentId())
                .studentName(updated.getStudent().getName())
                .build();
    }

    // DELETE
    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense == null) throw new RuntimeException("Expense not found");

        expenseRepository.delete(expense);
    }
}
package com.expense.repository;

import com.expense.model.Category;
import com.expense.model.Expense;
import com.expense.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // All expenses of a student
    List<Expense> findByStudent(Student student);

    // Filter by category
    List<Expense> findByStudentAndCategory(Student student, Category category);

    // Filter by date range
    List<Expense> findByStudentAndDateBetween(
            Student student,
            LocalDate startDate,
            LocalDate endDate
    );

    // Sort by latest
    List<Expense> findByStudentOrderByDateDesc(Student student);
}

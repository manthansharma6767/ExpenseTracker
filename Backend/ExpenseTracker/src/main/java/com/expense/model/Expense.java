package com.expense.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Size(max = 255)
    private String description;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    // Many expenses → one student
    @ManyToOne()
    private Student student;
}
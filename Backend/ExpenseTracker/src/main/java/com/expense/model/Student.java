package com.expense.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String name;

    // One student → many expenses
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Expense> expenses;
}
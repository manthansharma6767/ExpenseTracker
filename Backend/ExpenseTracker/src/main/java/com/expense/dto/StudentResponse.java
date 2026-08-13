package com.expense.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {

    private Long studentId;
    private String name;

}

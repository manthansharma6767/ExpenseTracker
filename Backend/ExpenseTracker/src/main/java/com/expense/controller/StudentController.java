package com.expense.controller;

import com.expense.dto.StudentRequest;
import com.expense.dto.StudentResponse;
import com.expense.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public StudentResponse createStudent(@RequestBody StudentRequest request) {
        return studentService.createStudent(request);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentBYId(@PathVariable Long id) {
        return studentService.getByStudentId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteStudentId(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

}

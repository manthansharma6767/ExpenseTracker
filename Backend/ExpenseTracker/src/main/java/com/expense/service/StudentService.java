package com.expense.service;

import com.expense.dto.StudentRequest;
import com.expense.dto.StudentResponse;
import com.expense.model.Student;
import com.expense.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    // create
    public StudentResponse createStudent(StudentRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .studentId(request.getStudentId())
                .build();

        Student savedStudent = studentRepository.save(student);

        return StudentResponse.builder()
                .studentId(savedStudent.getStudentId())
                .name(savedStudent.getName()).build();

    }

    // GET ALL
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(student -> StudentResponse.builder()
                        .name(student.getName())
                        .studentId(student.getStudentId())
                        .build())
                .toList();

    }

    public StudentResponse getByStudentId(Long id) {
        Student student = studentRepository.findById(id)
                .orElse(null);

        if(student == null) {
            throw new RuntimeException("Student Not Found");
        }

        return StudentResponse.builder()
                .name(student.getName())
                .studentId(student.getStudentId())
                .build();
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student == null) {
            throw new RuntimeException("Student Not Exists");
        }
        studentRepository.deleteById(id);
    }

}

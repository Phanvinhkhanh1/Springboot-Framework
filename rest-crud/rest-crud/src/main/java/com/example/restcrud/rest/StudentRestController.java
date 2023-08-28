package com.example.restcrud.rest;

import com.example.restcrud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students = new ArrayList<>();

    @PostConstruct
    private void loadData() {
        students.add(new Student("Phan Thi", "Phuong"));
        students.add(new Student("Phan Vinh", "Tung"));
        students.add(new Student("Phan Vinh", "Khanh"));
    }

    @GetMapping("/students")
    private List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    private Student getStudentById(@PathVariable int studentId) {
        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Not found student with id = " + studentId);
        }
        return students.get(studentId);
    }
}

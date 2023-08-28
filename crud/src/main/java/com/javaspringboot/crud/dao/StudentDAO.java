package com.javaspringboot.crud.dao;

import com.javaspringboot.crud.entity.Student;

import java.util.List;

public interface StudentDAO {
    public void saveStudent(Student student);

    Student findById(Long id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    Student updateStudent(Student student);

    void deleteById(Long id);

    int deleteAll();
}

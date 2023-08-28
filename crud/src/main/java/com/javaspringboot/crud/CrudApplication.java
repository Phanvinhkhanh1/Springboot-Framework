package com.javaspringboot.crud;

import com.javaspringboot.crud.dao.StudentDAO;
import com.javaspringboot.crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    /**
     * @param
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
//            readStudent(studentDAO);
//            queryForStudents(studentDAO);
//            queryStudentByLastName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO);
            deleteAll(studentDAO);
        };
    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("Create new student object ...");
        Student student = new Student("Le", "Hoang Anh", "hoangAnhLe1990@gmail.com");

        System.out.println("Saving the student ...");
        studentDAO.saveStudent(student);

        System.out.println("Saved student. Generated Id : " + student.getId());
    }

    private void readStudent(StudentDAO studentDAO) {
        //Create new student
        System.out.println("Create new Student");
        Student student = new Student("Phan Vinh", "Khanh", "Khanh161297@gmail.com");

        //Save student into database
        System.out.println("Saving student");
        studentDAO.saveStudent(student);

        //Retrieve student with id = 2
        System.out.println("Retrieving student with id = " + student.getId());
        Student theStudent = studentDAO.findById(student.getId());

        System.out.println(theStudent);
    }

    private void queryForStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();
        students.forEach(s -> System.out.println(s.toString()));
    }

    private void queryStudentByLastName(StudentDAO studentDAO) {
        String lastName = "Khanh";
        List<Student> students = studentDAO.findByLastName(lastName);
        students.forEach(s -> System.out.println(s.toString()));
    }

    private void updateStudent(StudentDAO studentDAO) {
        Student student = studentDAO.findById(2L);
        System.out.println("Old email : " + student.getEmail());
        student.setEmail("PhanVinhKhanh1997@gmail.com");

        System.out.println("Update student ...");
        Student theStudent = studentDAO.updateStudent(student);
        System.out.println("New email : " + theStudent.getEmail());
    }

    private void deleteStudent(StudentDAO studentDAO) {
        Student student = studentDAO.findById(1L);
        System.out.println("Delete student with id = " + student.getId());
        studentDAO.deleteById(student.getId());
    }

    private void deleteAll(StudentDAO studentDAO) {
        int numberDeletedRows = studentDAO.deleteAll();
        System.out.println("Number of rows are deleted : " + numberDeletedRows);
    }
}

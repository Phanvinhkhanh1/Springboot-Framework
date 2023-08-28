package com.javaspringboot.crud.dao;

import com.javaspringboot.crud.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s order by s.lastName desc", Student.class);
        List<Student> results = query.getResultList();
        return results;
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.lastName=:theData", Student.class);
        query.setParameter("theData", lastName);
        List<Student> results = query.getResultList();

        return results;
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        return entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numberDeletedRows = entityManager.createQuery("delete from Student").executeUpdate();

        return numberDeletedRows;
    }
}

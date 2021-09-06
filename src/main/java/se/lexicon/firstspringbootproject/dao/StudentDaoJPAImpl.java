package se.lexicon.firstspringbootproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.firstspringbootproject.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class StudentDaoJPAImpl implements StudentDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Student persist(Student student) {
        //Student id 0
        entityManager.persist(student);
        //Student id = DataBase assignment
        return student;
    }

    @Override
    public Student findById(int studentId) {
        return entityManager.find(Student.class, studentId);
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public void remove(Student student) {

    }

    @Override
    public Student merge(Student student) {
        return null;
    }
}

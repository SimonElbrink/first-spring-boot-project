package se.lexicon.firstspringbootproject.dao;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.firstspringbootproject.exceptions.InvalidNameException;
import se.lexicon.firstspringbootproject.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class StudentDaoJPAImpl implements StudentDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class, InvalidNameException.class})
    public Student persist(Student student) throws IllegalArgumentException, InvalidNameException{
        if (student == null) throw new IllegalArgumentException("Student is not allowed to be null");

        if (student.getFirstName().length() <3 ) throw new InvalidNameException("Student is not allowed to have a name less than 3 Characters");

        //Student id 0
        entityManager.persist(student);
        //Student id = DataBase assignment

        return student;
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(int studentId) {
        return entityManager.find(Student.class, studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findByFirstName(String firstName) {
        return null;
    }

    @Override
    @Transactional
    public void remove(Student student) {
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public Student merge(Student student) {
        return entityManager.merge(student);
    }
}

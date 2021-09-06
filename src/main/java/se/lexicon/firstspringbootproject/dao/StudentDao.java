package se.lexicon.firstspringbootproject.dao;

import se.lexicon.firstspringbootproject.model.Student;

import java.util.List;

public interface StudentDao {

    Student persist(Student student);

    Student findById(int studentId);

    List<Student> findAll();

    List<Student> findByFirstName(String firstName);

    void remove(Student student);

    Student merge(Student student);


}

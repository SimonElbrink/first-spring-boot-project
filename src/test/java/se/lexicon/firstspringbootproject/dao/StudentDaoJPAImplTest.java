package se.lexicon.firstspringbootproject.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.firstspringbootproject.exceptions.InvalidNameException;
import se.lexicon.firstspringbootproject.model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class StudentDaoJPAImplTest {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    TestEntityManager testEntityManager;


    private Student testStudent;
    private int testId;


    @BeforeEach
    void setup(){
        testStudent = testEntityManager.persist(new Student("Simon", "Elbrink", "simon@lexicon.se"));
        testId = testStudent.getId();
    }


    @Test
    void testPersist() {
        Student student = new Student("Test", "Testsson", "test@test.com");
        assertEquals(0, student.getId());

//        System.out.println("Before persist student = " + student);
        studentDao.persist(student);

        assertTrue(student.getId() != 0,"Student ID was 0!");

//        System.out.println("After Persist student = " + student);
    }

    @Test
    @DisplayName("Persisting Null should throw IllegalArgumentException")
    void test_persist_null_should_throw_IllegalArgumentException(){

        // Arrange
        Student student = null;

        // Act
        // Assert
        //Will throw otherException
        assertThrows(IllegalArgumentException.class,
                ()-> studentDao.persist(student)
                );

    }

    @Test
    @DisplayName("Persisting Student with firstname shorter the 3 throw InvalidNameException")
    void test_persist_short(){
        Student student = new Student("TT", null, null);

        assertThrows(InvalidNameException.class, ()-> studentDao.persist(student), "Persisting did not Throw expected Exception");

    }

    @Test
    void testFindById() {
        // Arrange
        Student foundById = null;

        // Act
        foundById = studentDao.findById(testId);

        // Assert
        assertNotNull(foundById);
        assertEquals(foundById.getFirstName(), testStudent.getFirstName());
        System.out.println(foundById);

    }


    @Test
    void remove() {
        assertNotNull(testStudent);

        studentDao.remove(testStudent);

        assertNull(testEntityManager.find(Student.class, testId), "Student was Found!");
    }
}
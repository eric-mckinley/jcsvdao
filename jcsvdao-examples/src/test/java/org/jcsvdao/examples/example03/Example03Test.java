package org.jcsvdao.examples.example03;

import org.jcsvdao.CSVDao;
import org.jcsvdao.CSVDaoFactory;
import org.jcsvdao.examples.example03.model.PostgradStudent;
import org.jcsvdao.examples.example03.model.Student;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 */
public class Example03Test {

    private static CSVDao dao;

    @BeforeClass
    public static void setup() {
        CSVDaoFactory factory = new CSVDaoFactory("/example03/csv-config.xml");
        dao = new CSVDao(factory);
    }

    @Test
    public void find_allStudents_noErrors() {
        List<Student> students = dao.find(Student.class);
        assertEquals(students.size(), 7L);
    }

    @Test
    public void find_allPostgradStudents_noErrors() {
        List<PostgradStudent> postgrads = dao.find(PostgradStudent.class);
        assertEquals(postgrads.size(), 2L);
    }

    @Test
    public void find_studentByPK_noErrors() {
        Student student1 = dao.get(Student.class, 1000016L);
        Student student2 = dao.get(Student.class, 1000017L);
        PostgradStudent student3 = dao.get(PostgradStudent.class, 1000017L);

        assertNotNull(student1);
        assertNotNull(student2);
        assertNotNull(student3);

        assertEquals("John", student1.getFirstName());
        assertEquals("Nelly", student2.getFirstName());
        assertEquals("Nelly", student3.getFirstName());
    }

}

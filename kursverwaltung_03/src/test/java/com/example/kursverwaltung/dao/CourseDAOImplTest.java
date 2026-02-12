package com.example.kursverwaltung.dao;

import com.example.kursverwaltung.db.DBConnect;
import com.example.kursverwaltung.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseDAOImplTest {
    CourseDAOImpl dao;

    Connection con = new DBConnect().connection();

    @BeforeEach
    void setUp() {  // wird vor jedem Test ausgeführt
        dao = new CourseDAOImpl();
        try ( Statement st = con.createStatement()){
            st.execute("TRUNCATE TABLE kurs");//komplett Reset/ auch IDs
          //  st.execute("DELETE FROM kurs");
            dao.save(new Course("Java", LocalDate.now(), "Otto",2 ));
            dao.save(new Course("BWL", LocalDate.now(), "Ina",4 ));
            dao.save(new Course("Java2", LocalDate.now(), "Max",3 ));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testSave() {
        boolean saved = dao.save(new Course("Wirtschaft", LocalDate.now(), "Anton",2 ));
        assertTrue(saved);
        List<Course> li= dao.findByKursname("Wirtschaft");
        Course findCourse = li.get(0);
        assertNotNull(findCourse);

        assertEquals("Anton", findCourse.getTeacher());

    }

    @Test
    void testSaveDuplicateName() {

        assertThrows(DuplicateKeyException.class, () -> {
            dao.save(new Course("Java2", LocalDate.now(), "Max",3 ));
        });

    }
}
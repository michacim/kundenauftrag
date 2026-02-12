package com.example.kursverwaltung.dao;

import com.example.kursverwaltung.db.DBConnect;
import com.example.kursverwaltung.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private Connection con = new DBConnect().connection();//FIXME static method


    @Override
    public boolean save(Course course) {

        final String q = """
                INSERT INTO kurs (name, kursbeginn, dozentname, wochen)
                VALUES (?,?,?,?)
                """;
        try (PreparedStatement ps = con.prepareStatement(q)) {
            ps.setString(1, course.getName());
            ps.setDate(2, Date.valueOf(course.getCourseStart()));
            ps.setString(3, course.getTeacher());
            ps.setInt(4, course.getWeeks());
            int n = ps.executeUpdate();
            return n == 1;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(">>>>" + e);
            //throw new ();
            throw new DuplicateKeyException("Kursname schon vorhanden!");
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<Course>();
        try {
            var ps = con.prepareStatement("SELECT * FROM kurs");
            var rs = ps.executeQuery();
            createList(rs, courses);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    private static void createList(ResultSet rs, List<Course> courses) throws SQLException {
        while (rs.next()) {
            var id = rs.getInt("id");
            var name = rs.getString("name");
            var courseStart = rs.getDate("kursbeginn").toLocalDate();
            var teacherName = rs.getString("dozentname");
            var weeks = rs.getInt("wochen");
            courses.add(new Course(id, name, courseStart, teacherName, weeks));
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var ps = con.prepareStatement("DELETE FROM kurs WHERE id=?")) {


            ps.setInt(1, id);
            int n = ps.executeUpdate();
            return n == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    // Java1, Java2   ja
    @Override
    public List<Course> findByKursname(String kursname) {
        List<Course> courses = new ArrayList<>();
        try {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM kurs WHERE LOWER(name) LIKE ?");
            ps.setString(1, "%" + kursname.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();

            createList(rs, courses);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public boolean update(Course updateCourse) {

        final String q= """
                UPDATE kurs
                SET name=?, kursbeginn=?, dozentname=?,wochen=?
                WHERE id=?
                """;

        try (  PreparedStatement ps = con.prepareStatement(q)){
            ps.setString(1, updateCourse.getName());
            ps.setDate(2, Date.valueOf(updateCourse.getCourseStart()));
            ps.setString(3, updateCourse.getTeacher());
            ps.setInt(4,updateCourse.getWeeks());
            ps.setInt(5,updateCourse.getId());

            int n= ps.executeUpdate();
            return n==1;

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicateKeyException("Kursname schon vorhanden");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}

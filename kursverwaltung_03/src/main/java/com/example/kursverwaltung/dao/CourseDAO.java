package com.example.kursverwaltung.dao;

import com.example.kursverwaltung.model.Course;

import java.util.List;

public interface CourseDAO {

    boolean save(Course course);
    List<Course> findAll();
    boolean deleteById(int id);

    List<Course> findByKursname(String kursname);


    boolean update(Course updateCourse);
}

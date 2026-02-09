package com.example.kursverwaltung.model;

import java.time.LocalDate;

public class Kurs {

    private int id;
    private String name;
    private LocalDate courseStart;
    private String teacher;
    private int weeks;

    public Kurs(int id, String name, LocalDate courseStart, String teacher, int weeks) {
        this.id = id;
        this.name = name;
        this.courseStart = courseStart;
        this.teacher = teacher;
        this.weeks = weeks;
    }

    public Kurs(String name, LocalDate courseStart, String teacher, int weeks) {
        this.name = name;
        this.courseStart = courseStart;
        this.teacher = teacher;
        this.weeks = weeks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(LocalDate courseStart) {
        this.courseStart = courseStart;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseStart=" + courseStart +
                ", teacher='" + teacher + '\'' +
                ", weeks=" + weeks +
                '}';
    }
}

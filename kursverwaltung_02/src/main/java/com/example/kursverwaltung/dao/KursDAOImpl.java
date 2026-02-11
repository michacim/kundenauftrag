package com.example.kursverwaltung.dao;

import com.example.kursverwaltung.db.DBConnect;
import com.example.kursverwaltung.model.Kurs;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KursDAOImpl implements KursDAO{
    private Connection con = new DBConnect().connection();//FIXME static method


    @Override
    public boolean save(Kurs kurs)  {

        final String q = """
                INSERT INTO kurs (name, kursbeginn, dozentname, wochen)
                VALUES (?,?,?,?)
                """;
        try ( PreparedStatement ps = con.prepareStatement(q)){
           ps.setString(1,kurs.getName());
           ps.setDate(2, Date.valueOf(kurs.getCourseStart()));
           ps.setString(3,kurs.getTeacher());
           ps.setInt(4,kurs.getWeeks());
           int n=ps.executeUpdate();
           return n==1;

        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println(">>>>"+e);
            //throw new ();
            throw new DuplicateKeyException("Kursname schon vorhanden!");
        } catch (Exception e){
            throw new RuntimeException("");
        }
    }

    @Override
    public List<Kurs> findAll() {
        ArrayList<Kurs> courses = new ArrayList<>();
        try {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM kurs");
            ResultSet rs =  ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate courseStart = rs.getDate("kursbeginn").toLocalDate();
                String dozname = rs.getString("dozentname");
                int weeks = rs.getInt("wochen");
                courses.add(new Kurs(id,name,courseStart,dozname,weeks ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public boolean deleteById(int id) {
        try {

            PreparedStatement ps = con.prepareStatement("DELETE FROM kurs WHERE id=?");
            ps.setInt(1,id);
            int n = ps.executeUpdate();
            return n==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       
    }

    @Override
    public Kurs findByKursname(String kursname) {
        return null;
    }
}

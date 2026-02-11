package com.example.kursverwaltung.dao;

import com.example.kursverwaltung.model.Kurs;

import java.util.List;

public interface KursDAO {

    boolean save(Kurs kurs);
    List<Kurs> findAll();
    boolean deleteById(int id);

    List<Kurs> findByKursname(String kursname);


    boolean update(Kurs  updateKurs);
}

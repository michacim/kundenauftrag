package com.example.kursverwaltung.dao;

import com.example.kursverwaltung.model.Kurs;

import java.util.List;

public class KursDAOImpl implements KursDAO{
    @Override
    public boolean save(Kurs kurs) {
        return false;
    }

    @Override
    public List<Kurs> findAll() {
        return List.of();
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}

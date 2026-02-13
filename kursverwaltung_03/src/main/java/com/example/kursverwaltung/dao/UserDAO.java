package com.example.kursverwaltung.dao;

import com.example.kursverwaltung.model.User;

public interface UserDAO {
    User authenticate(String username, String password);
}

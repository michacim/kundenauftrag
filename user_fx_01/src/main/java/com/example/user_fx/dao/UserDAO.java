package com.example.user_fx.dao;

import com.example.user_fx.model.User;

import java.util.List;

public interface UserDAO {

    boolean save(User newUser);
    boolean delete(int id);
    List<User> findAll();
}

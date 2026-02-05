package com.example.user_fx.dao;

import com.example.user_fx.db.DBConnect;
import com.example.user_fx.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUserDAO implements UserDAO{

    private DBConnect db = new DBConnect();
    @Override
    public boolean save(User newUser) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection con = db.connection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user");
            ResultSet rs =  ps.executeQuery();

            while(rs.next()){

                int id = rs.getInt("id");
                String username = rs.getString("username");
                System.out.println(id);
                users.add(new User(id,username));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}

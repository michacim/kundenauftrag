package com.example.user_fx.dao;

import com.example.user_fx.db.DBConnect;
import com.example.user_fx.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUserDAO implements UserDAO{

    private DBConnect db = new DBConnect();
    @Override
    public boolean save(User newUser) {
        try {
            Connection con = db.connection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO user (username) VALUES (?)");
            ps.setString(1,newUser.getUsername());//1== 1.Fragezeichen
            int n=  ps.executeUpdate(); //insert, delete, update
           return n==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(int id) {
        try {
            Connection con = db.connection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM user WHERE id=?");
            ps.setInt(1,id);
            int n = ps.executeUpdate();

            return n==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

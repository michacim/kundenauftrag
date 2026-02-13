package com.example.kursverwaltung.dao;

import com.example.kursverwaltung.db.DBConnect;
import com.example.kursverwaltung.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO{
    private Connection con = new DBConnect().connection();
    @Override
    public User authenticate(String username, String password) {
        final String q = "SELECT id, username FROM user WHERE username=? AND password=?";

        try (PreparedStatement ps = con.prepareStatement(q)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String uname = rs.getString("username");
                    return new User(id, uname);
                }
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

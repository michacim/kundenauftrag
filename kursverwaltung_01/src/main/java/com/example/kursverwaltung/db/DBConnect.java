package com.example.kursverwaltung.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private Connection con;


    public DBConnect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kurse", "root", "");
            System.out.println("Datenbank connect...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection connection() {
        return con;
    }

}

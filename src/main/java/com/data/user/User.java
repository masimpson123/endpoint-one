package com.data.user;

import java.io.InputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class User {
    public String create(String name, String phone) {
        try {
            Connection conn = connect();
            PreparedStatement stm = conn.prepareStatement(
            "INSERT INTO truth (name, phone) VALUES (?,?)");
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.executeUpdate();
            return "test user added!";
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return "fail!";
        }
    }

    public String fetch() {
        return "Here are all of our users:";
    }

    private Connection connect() throws SQLException {
         Properties properties = new Properties();
         try (InputStream resourceStream =
         Thread.currentThread()
                 .getContextClassLoader()
                 .getResourceAsStream("application.properties")) {
         properties.load(resourceStream);
         } catch (IOException e) {
            e.printStackTrace();
         }
         return DriverManager.getConnection(
            properties.getProperty("DATABASE_PATH"),
            properties.getProperty("DATABASE_USER"),
            properties.getProperty("DATABASE_PASSWORD"));
    }
}

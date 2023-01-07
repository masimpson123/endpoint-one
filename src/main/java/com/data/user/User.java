package com.data.user;

import java.io.InputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class User {
    public void create(String name, String phone) {
        try {
            Connection conn = connect();
            PreparedStatement stm = conn.prepareStatement(
            "INSERT INTO truth (name, phone) VALUES (?,?)");
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.executeUpdate();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public String fetch() {
        List<String> users = new ArrayList<>();
        try {
            Connection conn = connect();
            PreparedStatement stm = conn.prepareStatement(
                    "SELECT * FROM truth");
            ResultSet result = stm.executeQuery();
            while(result.next()) {
                users.add(result.getString(2));
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return "Here are all of our users:<br>" +
        users +
        "<br>-<br> Add yourself to our user list with this request:<br>" +
        "https://endpoint-one-2-u7qjhl7iia-uc.a.run.app/user?name=your-name&phone=your-phone";
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

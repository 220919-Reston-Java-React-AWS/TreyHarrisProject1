package com.revature.repository;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ConnectionFactory {
    public static Connection createConnection() throws SQLException {

        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);

        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";

        String username = "postgres";
        String password = "Mesoblack24362436";

        Connection connectionObject = DriverManager.getConnection(url, username, password);
        return connectionObject;
    }
}

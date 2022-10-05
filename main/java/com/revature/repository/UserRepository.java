package com.revature.repository;

import com.revature.model.User;
import java.sql.*;

public class UserRepository {
    public UserRepository() {
    }

    public User addUser(User user) throws SQLException {
        try (Connection connectionObject = ConnectionFactory.createConnection()){
            String sql = "insert into users (username, password, first_name, last_name, role_id) values (?,?,?,?,?)";

            PreparedStatement pstmt = connectionObject.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             pstmt.setString(1,user.getUsername());
             pstmt.setString(2,user.getPassword());
             pstmt.setString(3,user.getFirstname());
             pstmt.setString(4,user.getLastname());
             pstmt.setInt(5,1);

             int numberOfRecordsAdded = pstmt.executeUpdate();

             ResultSet rs = pstmt.getGeneratedKeys();

             rs.next();
             int id = rs.getInt(1);

             return new User(id, user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), 1);
        }
    }
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        try (Connection connectionObj = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM users as u WHERE u.username = ? AND u.password = ?";
            PreparedStatement pstmt = connectionObj.prepareStatement(sql);

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                String un = rs.getString("username");
                String pw = rs.getString("password");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int roleId = rs.getInt("role_id");

                return new User(id, un, pw, firstName, lastName, roleId);
            } else {
                return null;
            }
        }
    }
}

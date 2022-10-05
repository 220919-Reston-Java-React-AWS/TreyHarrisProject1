package com.revature.service;

import com.revature.exception.InvalidLoginException;
import com.revature.model.User;
import com.revature.repository.UserRepository;

import java.sql.SQLException;

public class AuthService {
    private static UserRepository userRepo = new UserRepository();
    public static User login(String username, String password) throws SQLException, InvalidLoginException {
        User user = userRepo.getUserByUsernameAndPassword(username, password);
        if (user == null){
            throw new InvalidLoginException("Invalid username and/or password");
        }
        return user;
    }
}

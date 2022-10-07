package com.revature.service;

import com.revature.exception.InvalidLoginException;
import com.revature.exception.MissingRegistrationInfoException;
import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.model.User;
import com.revature.repository.UserRepository;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class AuthService {
    private UserRepository userRepo = new UserRepository();
    public User login(String username, String password) throws SQLException, InvalidLoginException {
        User user = userRepo.getUserByUsernameAndPassword(username, password);
        if (user == null){
            throw new InvalidLoginException("Invalid username and/or password");
        }
        return user;
    }
    public void register(User user) throws SQLException, UserNameAlreadyTakenException, PSQLException, MissingRegistrationInfoException {
        if (userRepo.getUserByUsername(user.getUsername()) != null){
    throw new UserNameAlreadyTakenException("This username is already in use, choose again");
        }
        if (user.getUsername() == null || user.getPassword() == null || user.getFirstname() == null || user.getLastname() == null) {
            throw new MissingRegistrationInfoException("Please make sure you provided a username, password, and your first and last name");
        }
        userRepo.addUser(user);

    }


}

package com.revature.model;
import java.util.Objects;

public class User {

    private int id;
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private int roleId;

    public User() {
    }




    public User(int id, String username, String password, String firstName, String lastName, int roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }



    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }
}


package com.example.user.epicfitproject.model;

/**
 * Created by user on 3.9.2016 г..
 */
public class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        if(username != null && !username.isEmpty()) {
            this.username = username;
        }
        if(password != null && !password.isEmpty()) {
            this.password = password;
        }
        if(email != null && !email.isEmpty()) {
            this.email = email;
        }
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

    public String getEmail() {
        return email;
    }
}

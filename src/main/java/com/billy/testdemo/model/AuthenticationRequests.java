package com.billy.testdemo.model;

public class AuthenticationRequests {

    private String username;
    private String password;

    public AuthenticationRequests(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequests() {
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
}

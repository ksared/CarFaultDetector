package com.example.carfaultdetector.model;

import android.app.Application;

public class User extends Application {
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

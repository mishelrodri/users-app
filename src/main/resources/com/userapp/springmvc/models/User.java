package com.userapp.springmvc.models;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer rol;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, Integer rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public User(Integer id, String username, Integer rol) {
        this.id = id;
        this.username = username;
        this.rol = rol;
    }

    public User(Integer id, String username, String password, Integer rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
}

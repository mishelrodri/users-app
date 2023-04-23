package com.userapp.springmvc.dao;

import com.userapp.springmvc.models.User;

import java.util.List;

public interface UserDAO {

    public int save(User user);
    public int update(User user);
    public User get(Integer id);
    public int delete(Integer id);
    public List<User> list();

}

package com.wl.service;

import com.wl.entity.User;

import java.util.List;


public interface UserService {
    public void saveUsers(List<User> us);
    public List<User> getAllUsernames();
    public User getByNameAndPwd(String user,String pwd);
}

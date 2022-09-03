package com.projecttest.springadvproject.service;

import com.projecttest.springadvproject.entities.User;

public interface UserService {

    User  saveToDB(User user ,String usermail,String username,String userpassword);
    User getUsername(String username);

}

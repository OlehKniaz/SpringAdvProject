package com.projecttest.springadvproject.service.Impl;

import com.projecttest.springadvproject.dao.UserDao;
import com.projecttest.springadvproject.entities.User;
import com.projecttest.springadvproject.service.EmailService;
import com.projecttest.springadvproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao, EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userDao=userDao;
        this.emailService = emailService;
    }

    @Override
    public User saveToDB(User user ,String usermail,String username,String userpassword) {
        String encoded = passwordEncoder.encode(userpassword);
        user.setUserMail(usermail);
        user.setUsername(username);
        user.setUserPassword(encoded);
        User saved = userDao.save(user);
        return saved;
    }

    @Override
    public User getUsername(String username) {
        User byUsername = userDao.getByUserWithoutOptinal(username);
        return byUsername;
    }


}

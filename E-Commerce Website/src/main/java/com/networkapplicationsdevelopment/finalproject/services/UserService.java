package com.networkapplicationsdevelopment.finalproject.services;

import javax.transaction.Transactional;

import com.networkapplicationsdevelopment.finalproject.dao.UserDAO;
import com.networkapplicationsdevelopment.finalproject.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public boolean userExists(String email) {
        return userDAO.exists(email);
    }

    public boolean userExists(String email, String password) {
        return userDAO.exists(email, password);
    }

    public User getUserByEmail(String email) {
        return userDAO.retrieve(email);
    }

    public User getUserById(int id) {
        return userDAO.retrieve(id);
    }
}
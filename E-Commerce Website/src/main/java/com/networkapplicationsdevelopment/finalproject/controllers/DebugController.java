package com.networkapplicationsdevelopment.finalproject.controllers;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networkapplicationsdevelopment.finalproject.dao.UserDAO;
import com.networkapplicationsdevelopment.finalproject.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


// JUST FOR DEBUGGING PURPOSES !!!
@Controller
public class DebugController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping(value = "/user_session_info", produces = "application/json")
    @ResponseBody
    private String getSessionInfo(HttpSession session) throws JsonProcessingException {
        Enumeration<String> arr = (Enumeration<String>)session.getAttributeNames();
        HashMap<String, Object> info = new HashMap<String, Object>();
        while (arr.hasMoreElements()) {
            String name = arr.nextElement();
            info.put(name, session.getAttribute(name));
        }
        return new ObjectMapper().writeValueAsString(info);
    }

    @GetMapping(value = "/user_info/{uid}", produces = "application/json")
    @ResponseBody
    private String getUserInfo(HttpSession session, @PathVariable("uid") int uid) throws JsonProcessingException {
        User user = userDAO.retrieve(uid);
        return new ObjectMapper().writeValueAsString(user);
    }
}
package com.networkapplicationsdevelopment.finalproject.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networkapplicationsdevelopment.finalproject.models.User;
import com.networkapplicationsdevelopment.finalproject.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = { "", "/" })
    private String viewLogin(Model model, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("uid") != null) {
            response.setHeader("location", "/");
            response.setStatus(302);
        }
        model.addAttribute("title", "Login to your account");
        model.addAttribute("loggedIn", (session.getAttribute("uid") != null));
        model.addAttribute("content", "Login");
        return "Layout";
    }

    @PostMapping(value = { "", "/" }, produces = "application/json")
    @ResponseBody
    private String verifyLogin(Model model, HttpSession session, HttpServletResponse response,
            @RequestParam("email") String email, @RequestParam("password") String password)
            throws JsonProcessingException {
        if (session.getAttribute("uid") != null) {
            response.setHeader("location", "/");
            response.setStatus(302);
        }

        HashMap<String, Object> responseProperties = new HashMap<String, Object>();

        boolean userExists = userService.userExists(email, password);
        responseProperties.put("successful", userExists);
        if (userExists) {
            User user = userService.getUserByEmail(email);
            responseProperties.put("email", email);
            session.setAttribute("uid", user.getId());
            session.setAttribute("username", user.getUsername());
        }
        
        return new ObjectMapper().writeValueAsString(responseProperties);
    }
}
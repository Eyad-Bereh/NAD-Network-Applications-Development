package com.networkapplicationsdevelopment.finalproject.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping(value = { "", "/" })
    public void logout(HttpServletResponse response, HttpSession session) {
        if (session.getAttribute("uid") != null) {
            session.invalidate();
        }
        response.setHeader("Location", "/");
        response.setStatus(302);
    }
}
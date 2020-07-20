package com.networkapplicationsdevelopment.finalproject.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.networkapplicationsdevelopment.finalproject.models.User;
import com.networkapplicationsdevelopment.finalproject.services.PurchaseService;
import com.networkapplicationsdevelopment.finalproject.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping(value = { "", "/" })
    public String viewUser(HttpSession session, HttpServletResponse response, Model model) {
        model.addAttribute("title", "Welcome to computers & electronic devices website");
        model.addAttribute("loggedIn", (session.getAttribute("uid") != null));
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));

        int uid = (int)session.getAttribute("uid");
        User user = userService.getUserById(uid);
        List<HashMap<String, Object>> purchases = purchaseService.getPurchases(uid);

        model.addAttribute("content", "User");
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userBalance", user.getBalance());
        model.addAttribute("purchases", purchases);
        return "Layout";
    }
}
package com.networkapplicationsdevelopment.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.networkapplicationsdevelopment.finalproject.services.ProductService;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = { "/", "/index", "/home" })
    private String index(Model model, HttpSession session) {
        List<Integer> gamingLaptopsCategories = Arrays.asList(1, 3);
        List<Integer> normalLaptopsCategories = Arrays.asList(1);
        List<Integer> desktopsCategories = Arrays.asList(2);
        List<Integer> monitorsCategories = Arrays.asList(4);
        List<Integer> tabletsCategories = Arrays.asList(5);
        List<Integer> accessoriesCategories = Arrays.asList(6);

        model.addAttribute("title", "Welcome to computers & electronic devices website");
        model.addAttribute("loggedIn", (session.getAttribute("uid") != null));
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));
        //model.addAttribute("userBalance", session.getAttribute("balance"));
        //model.addAttribute("userSessionID", session.getId());
        model.addAttribute("content", "Index");
        model.addAttribute("gamingLaptops", productService.getProductsByCategories(gamingLaptopsCategories, 3));
        model.addAttribute("normalLaptops", productService.getProductsByCategories(normalLaptopsCategories, 3));
        model.addAttribute("desktops", productService.getProductsByCategories(desktopsCategories, 3));
        model.addAttribute("monitors", productService.getProductsByCategories(monitorsCategories, 3));
        model.addAttribute("tablets", productService.getProductsByCategories(tabletsCategories, 3));
        model.addAttribute("accessories", productService.getProductsByCategories(accessoriesCategories, 3));

        return "Layout";
    }
}


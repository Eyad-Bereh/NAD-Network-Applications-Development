package com.networkapplicationsdevelopment.finalproject.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.networkapplicationsdevelopment.finalproject.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{category_name}")
    private String viewProductsByCategory(HttpSession session, HttpServletResponse response,
                    Model model, @PathVariable("category_name") String category_name) {
        model.addAttribute("title", "View product details");
        model.addAttribute("loggedIn", (session.getAttribute("uid") != null));
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));

        model.addAttribute("content", "Category");
        model.addAttribute("categoryName", category_name);
        model.addAttribute("products", productService.getProductsByCategory(category_name));

        return "Layout";
    }
}
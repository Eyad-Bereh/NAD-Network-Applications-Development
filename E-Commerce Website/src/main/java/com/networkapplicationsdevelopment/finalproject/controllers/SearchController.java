package com.networkapplicationsdevelopment.finalproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networkapplicationsdevelopment.finalproject.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/search")
    private String viewSearch(Model model, HttpSession session) {
        model.addAttribute("title", "View product details");
        model.addAttribute("loggedIn", (session.getAttribute("uid") != null));
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("userBalance", session.getAttribute("balance"));
        model.addAttribute("userSessionID", session.getId());
        model.addAttribute("content", "Search");
        model.addAttribute("categories", productService.getCategories());
        return "Layout";
    }

    @PostMapping(value = "/search", produces = "application/json")
    @ResponseBody
    private String searchWithCategories(@RequestParam("name") String name,
            @RequestParam(name = "categories[]", required = false) List<Integer> categories) throws JsonProcessingException {
        if (categories == null) {
            return new ObjectMapper().writeValueAsString(productService.getProductsByName(name)).toString();
        }
        return new ObjectMapper().writeValueAsString(productService.getProductsByNameAndCategories(name, categories)).toString();
    }
}
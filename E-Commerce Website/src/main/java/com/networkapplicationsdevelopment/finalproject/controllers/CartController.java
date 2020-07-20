package com.networkapplicationsdevelopment.finalproject.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networkapplicationsdevelopment.finalproject.misc.CartItem;
import com.networkapplicationsdevelopment.finalproject.models.Product;
import com.networkapplicationsdevelopment.finalproject.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/add", produces = "application/json")
    @ResponseBody
    @SuppressWarnings("unchecked")
    private String addProduct(@RequestParam("product_id") int product_id,
                            @RequestParam("quantity") int quantity,
                             HttpSession session, HttpServletResponse response) throws JsonProcessingException {
        
        // Check whether the user is logged in
        if (session.getAttribute("uid") == null) {
            response.setHeader("location", "/");
            response.setStatus(302);
        }
        HashMap<String, Object> finalResponse = new HashMap<String ,Object>();

        if (!productService.productExists(product_id)) {
            finalResponse.put("error", "The product you're trying to add doesn't exist in database");
            finalResponse.put("isOkay", false);
        }
        else {
            Product product = productService.getProductById(product_id);
            if (session.getAttribute("items") == null) {
                session.setAttribute("items", new HashMap<Integer, CartItem>());
            }

            CartItem item = new CartItem(product, quantity);
            HashMap<Integer, CartItem> items = (HashMap<Integer, CartItem>)session.getAttribute("items");
            items.put(product_id, item);
            session.setAttribute("items", items);

            finalResponse.put("isOkay", true);
        }

        return new ObjectMapper().writeValueAsString(finalResponse).toString();
    }

    @PostMapping(value = "/remove", produces = "application/json")
    @ResponseBody
    @SuppressWarnings("unchecked")
    private String removeProduct(@RequestParam("product_id") int product_id, 
                                HttpSession session,
                                HttpServletResponse response) throws JsonProcessingException {
        if (session.getAttribute("uid") == null) {
            response.setHeader("location", "/");
            response.setStatus(302);
        }

        HashMap<String, Object> finalResponse = new HashMap<String ,Object>();

        if (!productService.productExists(product_id)) {
            finalResponse.put("error", "The product you're trying to remove doesn't exist in database");
            finalResponse.put("isOkay", false);
        }
        else {
            if (session.getAttribute("items") != null) {
                HashMap<Integer, CartItem> items = (HashMap<Integer, CartItem>) session.getAttribute("items");
                if (items.get(product_id) != null) {
                    items.remove(product_id);
                    if (items.size() == 0) {
                        items = null;
                    }
                    session.setAttribute("items", items);
                    finalResponse.put("isOkay", true);
                }
                else {
                    finalResponse.put("error", "The product you're trying to remove doesn't exist in the cart");
                    finalResponse.put("isOkay", false);
                }
            }
        }

        return new ObjectMapper().writeValueAsString(finalResponse).toString();
    }

    @PostMapping(value = "/retrieve", produces = "application/json")
    @ResponseBody
    @SuppressWarnings("unchecked")
    private String getProducts(HttpServletResponse response, HttpSession session) throws JsonProcessingException {
        HashMap<Integer, CartItem> items = (HashMap<Integer, CartItem>)session.getAttribute("items");
        return new ObjectMapper().writeValueAsString(items);
    }
}
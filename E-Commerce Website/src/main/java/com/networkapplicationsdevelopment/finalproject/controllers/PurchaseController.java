package com.networkapplicationsdevelopment.finalproject.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networkapplicationsdevelopment.finalproject.misc.CartItem;
import com.networkapplicationsdevelopment.finalproject.services.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping(value = { "", "/" })
    @SuppressWarnings("unchecked")
    public String viewPurchase(HttpSession session, HttpServletResponse response, Model model) {
        if (session.getAttribute("uid") == null) {
            response.setHeader("location", "/");
            response.setStatus(302);
        }

        model.addAttribute("title", "Welcome to computers & electronic devices website");
        model.addAttribute("loggedIn", (session.getAttribute("uid") != null));
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));

        HashMap<Integer, CartItem> items = (HashMap<Integer, CartItem>)session.getAttribute("items");
        if (items == null || items.size() == 0) {
            model.addAttribute("itemsExist", false);
        }
        else {
            model.addAttribute("itemsExist", true);
            List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>(); 
            List<Integer> keys = new ArrayList<Integer>(items.keySet());
            for (int i = 0; i < keys.size(); i++) {
                HashMap<String, Object> temp = new HashMap<String, Object>();
                CartItem item = items.get(keys.get(i));
                temp.put("productName", item.getProduct().getName());
                temp.put("productQuantity", item.getQuantity());
                temp.put("productUnitPrice", item.getProduct().getPrice());
                temp.put("productTotalPrice", item.getTotalPrice());
                temp.put("productId", item.getProduct().getId());
                result.add(temp);
            }

            model.addAttribute("items", result);
        }
        model.addAttribute("content", "Purchase");
        return "Layout";
    }

    @PostMapping(value = { "", "/" }, produces = "application/json")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String doPurchase(HttpSession session, HttpServletResponse response) throws JsonProcessingException {
        // Check whether a the user is logged in
        if (session.getAttribute("uid") == null) {
            response.setHeader("location", "/");
            response.setStatus(302);
        }

        int uid = (int)session.getAttribute("uid");
        HashMap<String, Object> responseObject = new HashMap<String, Object>();

        HashMap<Integer, CartItem> items = (HashMap<Integer, CartItem>)session.getAttribute("items");
        ArrayList<CartItem> purchasedItems = new ArrayList<CartItem>();
        for (CartItem item :items.values()) {
            purchasedItems.add(item);
        }
        boolean result = purchaseService.makePurchase(uid, session.getId(), purchasedItems);

        if (!result) {
            responseObject.put("error", "Purchase couldn't be completed. Insufficient balance.");
            responseObject.put("isOkay", false);
        }
        else {
            responseObject.put("isOkay", true);
            session.removeAttribute("items");
        }
        return new ObjectMapper().writeValueAsString(responseObject).toString();
    }
}
package com.networkapplicationsdevelopment.finalproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.networkapplicationsdevelopment.finalproject.misc.ProductNotFoundException;
import com.networkapplicationsdevelopment.finalproject.models.Product;
import com.networkapplicationsdevelopment.finalproject.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = { "/{product_name}" })
    private String viewProduct(@PathVariable("product_name") String product_name, 
                Model model, HttpSession session, HttpServletResponse response) {
        if (!productService.productExists(product_name)) {
            throw new ProductNotFoundException();
        }
        Product product = productService.getProductsByName(product_name).get(0);
        model.addAttribute("title", "View product details");
        model.addAttribute("loggedIn", (session.getAttribute("uid") != null));
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("userBalance", session.getAttribute("balance"));
        model.addAttribute("userSessionID", session.getId());
        model.addAttribute("content", "Product");
        model.addAttribute("productId", product.getId());
        model.addAttribute("productName", product.getName());
        model.addAttribute("productImage", product.getImage());
        model.addAttribute("productCategories", productService.getCategoriesByProductName(product_name));
        model.addAttribute("productDescription", product.getDescription());
        model.addAttribute("productPrice", product.getPrice());
        return "Layout";
    }

    @GetMapping(value = { "", "/" })
    private String viewProducts(Model model, HttpSession session) {
        List<Product> products = productService.getProducts();
        model.addAttribute("title", "View product details");
        model.addAttribute("loggedIn", (session.getAttribute("uid") != null));
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("userBalance", session.getAttribute("balance"));
        model.addAttribute("userSessionID", session.getId());
        model.addAttribute("content", "Products");
        model.addAttribute("products", products);
        return "Layout";
    }
}
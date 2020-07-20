package com.networkapplicationsdevelopment.finalproject.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.networkapplicationsdevelopment.finalproject.dao.CategoryDAO;
import com.networkapplicationsdevelopment.finalproject.dao.ProductCategoryDAO;
import com.networkapplicationsdevelopment.finalproject.dao.ProductDAO;
import com.networkapplicationsdevelopment.finalproject.models.Category;
import com.networkapplicationsdevelopment.finalproject.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    public Product getProductById(int product_id) {
        return productDAO.retrieve(product_id);
    }

    public List<Product> getProducts() {
        return productDAO.retrieveAll();
    }

    public List<Product> getProducts(int limit) {
        return productDAO.retrieveLimit(limit);
    }

    public List<Product> getProductsByCategory(String category_name) {
        int category_id = categoryDAO.retrieve(category_name).getId();
        List<Integer> pids = productCategoryDAO.retrieveProducts(category_id);
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < pids.size(); i++) {
            Product product = productDAO.retrieve(pids.get(i));
            products.add(product);
        }
        return products;
    }

    public List<Product> getProductsByCategory(int category_id, int limit) {
        return productDAO.retrieveLimit(category_id, limit);
    }

    public List<Product> getProductsByCategories(List<Integer> categories, int limit) {
        List<Product> products = new ArrayList<Product>();

        List<List<Integer>> pidsByCategories = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            List<Integer> matchingProducts = productCategoryDAO.retrieveProducts(categories.get(i));
            pidsByCategories.add(matchingProducts);
        }

        List<Integer> allPids = pidsByCategories.get(0);
        for (int i = 1; i < pidsByCategories.size(); i++) {
            allPids.retainAll(pidsByCategories.get(i));
        }

        for (int i = 0; i < allPids.size(); i++) {
            int pid = allPids.get(i);
            Product product = productDAO.retrieve(pid);
            products.add(product);
        }

        int size = products.size();
        if (size > limit) {
            products = products.subList(size - 1 - limit, size - 1);
        }
        return products;
    }

    public List<Product> getProductsByIds(List<Integer> pids) {
        return productDAO.retrieveAll(pids);
    }

    public List<Product> getProductsByName(String name) {
        return productDAO.retrieve(name);
    }

    public List<Product> getProductsByNameAndCategories(String name, List<Integer> categories) {
        List<Product> products = new ArrayList<Product>();
        
        List<Product> productsByName = productDAO.retrieve(name);
        List<Integer> pidsByName = new ArrayList<Integer>();
        for (int i = 0; i < productsByName.size(); i++) {
            pidsByName.add(productsByName.get(i).getId());
        }
        List<List<Integer>> pidsByCategories = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            List<Integer> matchingProducts = productCategoryDAO.retrieveProducts(categories.get(i));
            pidsByCategories.add(matchingProducts);
        }

        List<Integer> allPids = pidsByName;
        for (int i = 0; i < pidsByCategories.size(); i++) {
            allPids.retainAll(pidsByCategories.get(i));
        }

        for (int i = 0; i < allPids.size(); i++) {
            int pid = allPids.get(i);
            Product product = productDAO.retrieve(pid);
            products.add(product);
        }

        return products;
    }

    public List<Category> getCategories() {
        return categoryDAO.retrieveAll();
    }

    public List<Category> getCategoriesByProductId(int product_id) {
        return productCategoryDAO.retrieveCategories(product_id);
    }

    public List<Category> getCategoriesByProductName(String product_name) {
        Product product = productDAO.retrieve(product_name).get(0);
        return productCategoryDAO.retrieveCategories(product.getId());
    }

    public boolean productExists(String product_name) {
        return productDAO.exists(product_name);
    }

    public boolean productExists(int product_id) {
        return (productDAO.retrieve(product_id) != null);
    }
}

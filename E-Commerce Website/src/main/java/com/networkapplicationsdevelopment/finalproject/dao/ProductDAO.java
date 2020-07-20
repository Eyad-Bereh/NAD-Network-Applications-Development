package com.networkapplicationsdevelopment.finalproject.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.networkapplicationsdevelopment.finalproject.models.Product;
import com.networkapplicationsdevelopment.finalproject.models.ProductCategory;

@Repository
@Transactional
public class ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Product product) {
        entityManager.persist(product);
    }

    public Product retrieve(int id) {
        return (Product)entityManager.find(Product.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Product> retrieve(String name) {
        return entityManager.createQuery("FROM Product WHERE name LIKE CONCAT('%', :name, '%')").setParameter("name", name).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Product> retrieveAll() {
        return entityManager.createQuery("FROM Product").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Product> retrieveAll(List<Integer> pids) {
        return entityManager.createQuery("FROM Product WHERE id IN :pids")
                .setParameter("pids", pids)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Product> retrieveLimit(int limit) {
        return entityManager.createQuery("FROM Product ORDER BY id DESC").setMaxResults(limit).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Product> retrieveLimit(int category_id, int limit) {
        List<ProductCategory> productsCategories = entityManager.createQuery("FROM ProductCategory WHERE category_id = :category_id ORDER BY product_id DESC").setParameter("category_id", category_id).setMaxResults(limit).getResultList();    
        List<Integer> products_ids = new ArrayList<>();
        for (int i = 0; i < productsCategories.size(); i++) {
            products_ids.add(productsCategories.get(i).getProductId());
        }
        List<Product> products = entityManager.createQuery("FROM Product WHERE id IN :pids").setParameter("pids", products_ids).getResultList();
        return products;
    }

    public void update(Product product) {
        entityManager.merge(product);
    }

    public boolean delete(Product product) {
        if (entityManager.contains(product)) {
            entityManager.remove(product);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean exists(String product_name) {
        List<Product> products = entityManager.createQuery("FROM Product WHERE name = :name").setParameter("name", product_name).getResultList();
        return (products.size() != 0);
    }
}
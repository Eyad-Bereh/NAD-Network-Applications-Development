package com.networkapplicationsdevelopment.finalproject.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.networkapplicationsdevelopment.finalproject.models.Category;
import com.networkapplicationsdevelopment.finalproject.models.ProductCategory;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ProductCategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(ProductCategory product_category) {
        entityManager.persist(product_category);
    }

    @SuppressWarnings("unchecked")
    public List<Category> retrieveCategories(int product_id) {
        List<ProductCategory> products = entityManager.createQuery("FROM ProductCategory WHERE product_id = :product_id").setParameter("product_id", product_id).getResultList();
        List<Integer> cids = new ArrayList<Integer>();
        for (int i = 0; i < products.size(); i++) {
            cids.add(products.get(i).getCategoryId());
        }
        List<Category> categories = entityManager.createQuery("FROM Category WHERE id IN :idlist").setParameter("idlist", cids).getResultList();
        return categories;
    }

    @SuppressWarnings("unchecked")
    public List<Integer> retrieveProducts(int category_id) {
        List<ProductCategory> productsCategories = entityManager.createQuery("FROM ProductCategory WHERE category_id = :category_id").setParameter("category_id", category_id).getResultList();
        List<Integer> pids = new ArrayList<Integer>();
        for (int i = 0; i < productsCategories.size(); i++) {
            pids.add(productsCategories.get(i).getProductId());
        }
        return pids;
    }
}
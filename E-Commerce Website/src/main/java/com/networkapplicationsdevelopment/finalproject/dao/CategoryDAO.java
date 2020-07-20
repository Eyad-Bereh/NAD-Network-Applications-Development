package com.networkapplicationsdevelopment.finalproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.persistence.PersistenceContext;

import com.networkapplicationsdevelopment.finalproject.models.Category;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Category category) {
        entityManager.persist(category);
    }

    public Category retrieve(int category_id) {
        return entityManager.find(Category.class, category_id);
    }

    public Category retrieve(String category_name) {
        return (Category)entityManager.createQuery("FROM Category WHERE name = :name")
                .setParameter("name", category_name)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Category> retrieveAll() {
        return entityManager.createQuery("FROM Category").getResultList();
    }
}

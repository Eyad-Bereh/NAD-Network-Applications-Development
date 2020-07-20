package com.networkapplicationsdevelopment.finalproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.networkapplicationsdevelopment.finalproject.models.User;

@Repository
@Transactional
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
        entityManager.persist(user);
    }

    public User retrieve(int uid) {
        return (User)entityManager.find(User.class, uid);
    }

    public User retrieve(String email) {
        return (User)entityManager.createQuery("FROM User WHERE email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> retrieveAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    @SuppressWarnings("unchecked")
    public boolean exists(String email) {
        List<User> users = entityManager.createQuery("FROM User WHERE email = :email").setParameter("email", email).getResultList();
        return (users.size() != 0);
    }

    @SuppressWarnings("unchecked")
    public boolean exists(String email, String password) {
        List<User> users = entityManager.createQuery("FROM User WHERE email = :email AND password = :password")
                            .setParameter("email", email)
                            .setParameter("password", password)
                            .getResultList();
        return (users.size() != 0);
    }
}
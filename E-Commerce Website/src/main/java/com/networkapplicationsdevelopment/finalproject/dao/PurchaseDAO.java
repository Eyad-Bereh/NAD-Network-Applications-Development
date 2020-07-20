package com.networkapplicationsdevelopment.finalproject.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.networkapplicationsdevelopment.finalproject.misc.CartItem;
import com.networkapplicationsdevelopment.finalproject.models.Purchase;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PurchaseDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void create(int uid, int newBalance, String session_id, ArrayList<CartItem> items) {
        Purchase purchase;
        CartItem item;
        int timestamp;

        for (int i = 0; i < items.size(); i++) {
            purchase = new Purchase();
            item = items.get(i);
            timestamp = (int) Instant.now().getEpochSecond();

            purchase.setUserId(uid);
            purchase.setProductId(item.getProduct().getId());
            purchase.setQuantity(item.getQuantity());
            purchase.setPrice(item.getTotalPrice());
            purchase.setTimestamp(timestamp);
            purchase.setSessionId(session_id);
            
            entityManager.persist(purchase);
        }
    }

    /*
    @SuppressWarnings("unchecked")
    public List<Purchase> retrieve(int product_id) {
        return  entityManager
                .createQuery("FROM Purchase WHERE product_id = :product_id")
                .setParameter("product_id", product_id)
                .getResultList();
    }
    */

    @SuppressWarnings("unchecked")
    public List<Purchase> retrieve(int uid) {
        return entityManager
                .createQuery("FROM Purchase WHERE user_id = :user_id")
                .setParameter("user_id", uid)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Purchase> retrieve(String useremail, int product_id) {
        return entityManager
                .createQuery("FROM Purchase WHERE useremail = :useremail AND product_id = :product_id")
                .setParameter("useremail", useremail)
                .setParameter("product_id", product_id)
                .getResultList();
    }
}

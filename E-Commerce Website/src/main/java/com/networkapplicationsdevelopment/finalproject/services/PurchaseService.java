package com.networkapplicationsdevelopment.finalproject.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.networkapplicationsdevelopment.finalproject.dao.ProductDAO;
import com.networkapplicationsdevelopment.finalproject.dao.PurchaseDAO;
import com.networkapplicationsdevelopment.finalproject.dao.UserDAO;
import com.networkapplicationsdevelopment.finalproject.misc.CartItem;
import com.networkapplicationsdevelopment.finalproject.models.Product;
import com.networkapplicationsdevelopment.finalproject.models.Purchase;
import com.networkapplicationsdevelopment.finalproject.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    private PurchaseDAO purchaseDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public boolean makePurchase(int uid, String session_id, ArrayList<CartItem> items) {
        User user = userDAO.retrieve(uid);
        int currentBalance = userDAO.retrieve(uid).getBalance();
        int totalPrice = 0;
        for (int i = 0; i < items.size(); i++) {
            totalPrice += items.get(i).getTotalPrice();
        }
        if (totalPrice > currentBalance) {
            return false;
        }
        int newBalance = currentBalance - totalPrice;
        purchaseDAO.create(uid, newBalance, session_id, items);
        user.setBalance(newBalance);
        userDAO.update(user);
        return true;
    }

    public List<HashMap<String, Object>> getPurchases(int uid) {
        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
        List<Purchase> purchases = purchaseDAO.retrieve(uid);

        for (int i = 0; i < purchases.size(); i++) {
            Purchase purchase = purchases.get(i);
            int product_id = purchase.getId();
            Product product = productDAO.retrieve(product_id);
            Date productPurchaseDatetime = new Date(purchase.getTimestamp());
            DateFormat formattedDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            HashMap<String, Object> temp = new HashMap<String, Object>();
            temp.put("productName", product.getName());
            temp.put("productQuantity", purchase.getQuantity());
            temp.put("productUnitPrice", purchase.getPrice() / purchase.getQuantity());
            temp.put("productTotalPrice", purchase.getPrice());
            temp.put("productPurchaseDatetime", formattedDate.format(productPurchaseDatetime));
            temp.put("sessionId", purchase.getSessionId());
            result.add(temp);
        }

        return result;
    }
}
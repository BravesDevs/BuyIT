package com.devangmp_8909274.buyit.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FirebaseManager {
    private static DatabaseReference mDatabase;

    // Private constructor to prevent instantiation
    private FirebaseManager() {
    }

    // Initialize database reference if null and return it (Singleton Pattern)
    public static DatabaseReference getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }
        return mDatabase;
    }

    public static DatabaseReference getProducts() {
        return getDatabase().child("products");
    }

    public static DatabaseReference addProduct() {
        return getProducts().push();
    }

    public static DatabaseReference getUserCart(){
        return getDatabase().child("cart").child(Objects.requireNonNull(AuthManager.getInstance().getUid()));
    }

    public static DatabaseReference getUserCartProduct(String productId){
        return getUserCart().child(productId);
    }
}

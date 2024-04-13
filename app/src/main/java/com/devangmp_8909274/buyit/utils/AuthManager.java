package com.devangmp_8909274.buyit.utils;

import com.google.firebase.auth.FirebaseAuth;

public class AuthManager {
    private static FirebaseAuth authReference;

    private AuthManager() {
    }

    public static FirebaseAuth getInstance() {
        return FirebaseAuth.getInstance();
    }
}

package com.devangmp_8909274.buyit.Cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devangmp_8909274.buyit.Cart.adapters.CartAdapter;
import com.devangmp_8909274.buyit.Cart.models.CartProduct;
import com.devangmp_8909274.buyit.Product.adapters.ProductAdapter;
import com.devangmp_8909274.buyit.Product.models.Product;
import com.devangmp_8909274.buyit.R;
import com.devangmp_8909274.buyit.utils.FirebaseManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";
    private final List<CartProduct> cartProducts = new ArrayList<>();

    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        FirebaseManager.getUserCart().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot cartProductSnapshot : snapshot.getChildren()) {
                    String productId = cartProductSnapshot.getKey();
                    int quantity = cartProductSnapshot.getValue(Integer.class);
                    assert productId != null;
                    FirebaseManager.getProducts().child(productId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Product product = snapshot.getValue(Product.class);
                            if (product != null) {
                                cartProducts.add(new CartProduct(product, quantity));
                                setupRecyclerView();
                                updateTotal();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e(TAG, "onCancelled: ", error.toException());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: ", error.toException());
            }
        });


        Button checkoutButton = findViewById(R.id.checkoutButton);
        totalTextView = findViewById(R.id.totalTextView);
        checkoutButton.setOnClickListener(v -> navigateToCheckout());
    }

    @SuppressLint("SetTextI18n")
    private void updateTotal() {
        double total = 0;
        for (CartProduct cartProduct : cartProducts) {
            total += cartProduct.getPrice() * cartProduct.getQuantity();
        }
        totalTextView.setText("Total: $" + total);
    }

    private void navigateToCheckout() {
//        Intent intent = new Intent(this, CheckoutActivity.class);
//        startActivity(intent);
    }

    private void setupRecyclerView() {
        RecyclerView cartProductsRecyclerView = findViewById(R.id.cartProductsRV);
        CartAdapter cartAdapter = new CartAdapter(cartProducts);
        cartProductsRecyclerView.setAdapter(cartAdapter);
        cartProductsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
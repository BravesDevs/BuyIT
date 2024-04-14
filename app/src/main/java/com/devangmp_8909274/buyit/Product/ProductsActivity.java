package com.devangmp_8909274.buyit.Product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devangmp_8909274.buyit.Cart.CartActivity;
import com.devangmp_8909274.buyit.Product.adapters.ProductAdapter;
import com.devangmp_8909274.buyit.Product.models.Product;
import com.devangmp_8909274.buyit.utils.FirebaseManager;
import com.devangmp_8909274.buyit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private final List<Product> products = new ArrayList<>();
    private FloatingActionButton cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_products);

        FirebaseManager.getProducts().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                products.clear();
                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    products.add(product);
                }

                setupRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ProductsActivity", "Failed to read value.", error.toException());
            }
        });

        cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(v -> navigateToCart());
    }

    private void setupRecyclerView() {
        RecyclerView productsRecyclerView = findViewById(R.id.productsRV);
        ProductAdapter productAdapter = new ProductAdapter(products);

        productsRecyclerView.setAdapter(productAdapter);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void navigateToCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
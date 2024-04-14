package com.devangmp_8909274.buyit.Product.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devangmp_8909274.buyit.R;
import com.devangmp_8909274.buyit.Product.models.Product;
import com.devangmp_8909274.buyit.utils.FirebaseManager;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.products_card, parent, false);
        return new ProductViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

        @SuppressLint("DiscouragedApi") int resourceId = holder.itemView.getContext().getResources().getIdentifier(
                products.get(position).getImage(), "drawable",
                holder.itemView.getContext().getPackageName());

        holder.productImageView.setImageResource(resourceId);
        holder.productTitleTextView.setText(products.get(position).getTitle());
        holder.productBrandTextView.setText(products.get(position).getBrand());
        holder.productPriceTextView.setText('$' + String.valueOf(products.get(position).getPrice()));

        holder.addToCartButton.setOnClickListener(v -> {
            int productId = products.get(position).getId();
            DatabaseReference cart = FirebaseManager.getUserCart();

            cart.child(String.valueOf(productId)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int quantity = 0;
                    if (snapshot.exists()) {
                        quantity = snapshot.getValue(Integer.class);
                    }
                    quantity++;
                    cart.child(String.valueOf(productId)).setValue(quantity);
                    Toast.makeText(holder.itemView.getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ProductAdapter", "Error adding product to cart: " + error.getMessage());
                }
            });

            cart.child("total").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int total = 0;
                    if (snapshot.exists()) {
                        total = snapshot.getValue(Integer.class);
                    }
                    total += products.get(position).getPrice();
                    cart.child("total").setValue(total);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("ProductAdapter", "Error adding product to cart: " + error.getMessage());
                }
            });
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImageView;
        public TextView productTitleTextView;
        public TextView productBrandTextView;
        public TextView productPriceTextView;
        public MaterialButton addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productTitleTextView = itemView.findViewById(R.id.productTitleTextView);
            productBrandTextView = itemView.findViewById(R.id.productBrandTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}

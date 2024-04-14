package com.devangmp_8909274.buyit.Product.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devangmp_8909274.buyit.R;
import com.devangmp_8909274.buyit.Product.models.Product;
import com.google.android.material.button.MaterialButton;

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

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

        @SuppressLint("DiscouragedApi") int resourceId = holder.itemView.getContext().getResources().getIdentifier(
                products.get(position).getImage(), "drawable",
                holder.itemView.getContext().getPackageName());

        holder.productImageView.setImageResource(resourceId);
        holder.productTitleTextView.setText(products.get(position).getTitle());
        holder.productBrandTextView.setText(products.get(position).getBrand());
        holder.productPriceTextView.setText(products.get(position).getPrice());

        holder.addToCartButton.setOnClickListener(v -> {
            int productId = products.get(position).getId();
            // Add product to the cart
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

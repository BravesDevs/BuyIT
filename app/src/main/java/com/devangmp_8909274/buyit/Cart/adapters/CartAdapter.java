package com.devangmp_8909274.buyit.Cart.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devangmp_8909274.buyit.Cart.models.CartProduct;
import com.devangmp_8909274.buyit.R;
import com.devangmp_8909274.buyit.utils.FirebaseManager;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    List<CartProduct> cartProducts;

    public CartAdapter(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_product_card, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        int resourceId = holder.itemView.getContext().getResources().getIdentifier(
                cartProducts.get(position).getImage(), "drawable",
                holder.itemView.getContext().getPackageName());

        holder.productImageView.setImageResource(resourceId);

        holder.productTitleTextView.setText(cartProducts.get(position).getTitle());

        holder.productPriceTextView.setText('$' + String.valueOf(cartProducts.get(position).getPrice()));

        holder.productQuantityTextView.setText(String.valueOf(cartProducts.get(position).getQuantity()));

        holder.removeFromCartButton.setOnClickListener(v -> {
            cartProducts.remove(position);
            notifyItemRemoved(position);
        });


        holder.incrementButton.setOnClickListener(v -> {
            cartProducts.get(position).setQuantity(cartProducts.get(position).getQuantity() + 1);
            FirebaseManager.getUserCart().child(String.valueOf(cartProducts.get(position).getId() + 1)).setValue(cartProducts.get(position).getQuantity());
            notifyItemChanged(position);
        });

        holder.decrementButton.setOnClickListener(v -> {
            if (cartProducts.get(position).getQuantity() > 1) {
                cartProducts.get(position).setQuantity(cartProducts.get(position).getQuantity() - 1);
                FirebaseManager.getUserCart().child(String.valueOf(cartProducts.get(position).getId() + 1)).setValue(cartProducts.get(position).getQuantity());
            } else {
                cartProducts.remove(position);
                FirebaseManager.getUserCart().child(String.valueOf(cartProducts.get(position).getId() + 1)).removeValue();
            }
            notifyItemRemoved(position);
        });


    }

    @Override
    public int getItemCount() {
        return this.cartProducts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        public ImageView productImageView;
        public TextView productTitleTextView;
        public TextView productPriceTextView;
        public TextView productQuantityTextView;
        public ImageButton removeFromCartButton;
        public MaterialButton incrementButton;
        public MaterialButton decrementButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productTitleTextView = itemView.findViewById(R.id.productTitleTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);
            removeFromCartButton = itemView.findViewById(R.id.productDeleteButton);
            incrementButton = itemView.findViewById(R.id.productIncrementButton);
            decrementButton = itemView.findViewById(R.id.productDecrementButton);
        }
    }
}

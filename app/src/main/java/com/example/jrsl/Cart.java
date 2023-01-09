package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
public class Cart extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bindCountriesData();
        setUIRef();
    }
    private void setUIRef()
    {
        //Reference of RecyclerView
        mRecyclerView = findViewById(R.id.myCartList);
        //Linear Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Cart.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        CartItemArrayAdapter myRecyclerViewAdapter = new CartItemArrayAdapter(cartItems, new CartItemArrayAdapter.CartClickListener() {
            @Override
            public void onItemClicked(CartItem cartItem) {
                Toast.makeText(Cart.this, cartItem.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindCountriesData()
    {
        cartItems.add(new CartItem("New Collection",  "Darcy Collection","White Dress","M",1, 25, R.drawable.outfit2));
        cartItems.add(new CartItem("New Collection",  "Cancy Collection","Brown Coat","M",1, 65, R.drawable.outfit3));
    }

}
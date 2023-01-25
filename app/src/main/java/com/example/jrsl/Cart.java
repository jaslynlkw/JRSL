package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
public class Cart extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private final Context context;

    public Cart(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bindCartData();
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
        CartItemArrayAdapter myRecyclerViewAdapter = new CartItemArrayAdapter(context,cartItems, new CartItemArrayAdapter.CartClickListener() {
            @Override
            public void onItemClicked(CartItem cartItem) {
                Toast.makeText(Cart.this, cartItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindCartData()
    {
        cartItems.add(new CartItem("Aleya Collection","Extravagant Black Dress","M",1, 230.30, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630258/ANDE/clothing/aleya_extravagantblackdress_c96drc.jpg"));
        cartItems.add(new CartItem("Daniel Collection", "White Flared Jeans","M",1, 45.60, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630272/ANDE/clothing/daniel_whiteflaredjeans_osrtq6.jpg"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BackArrow:
                finish();
                break;
            case R.id.btnPlaceOrder:
                Intent i1 = new Intent(Cart.this, ChooseAddress.class);
                startActivity(i1);
                break;
            default:
                // Handle other views click
        }

    }
}
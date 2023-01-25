package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> orderDetails = new ArrayList<>();
    private final Context context;

    public OrderDetails(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        bindOrderDetailsData();
        setUIRef();
    }
    private void setUIRef()
    {
        //Reference of RecyclerView
        mRecyclerView = findViewById(R.id.myOrderDetailsList);
        //Linear Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderDetails.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        OrderDetailsItemArrayAdapter myRecyclerViewAdapter = new OrderDetailsItemArrayAdapter(context,orderDetails, new OrderDetailsItemArrayAdapter.CartClickListener() {
            @Override
            public void onItemClicked(CartItem orderDetails) {
                Toast.makeText(OrderDetails.this, orderDetails.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindOrderDetailsData()
    {
        orderDetails.add(new CartItem("Lyla Collection","Off Shoulder Top","M",1, 30.30, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630291/ANDE/clothing/lyla_offshouldertop_wy7xb9.jpg"));

    }
}
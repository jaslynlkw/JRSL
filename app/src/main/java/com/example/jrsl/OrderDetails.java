package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> orderDetails = new ArrayList<>();


    public OrderDetails() {

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
        OrderDetailsItemArrayAdapter myRecyclerViewAdapter = new OrderDetailsItemArrayAdapter(getApplicationContext(),orderDetails, new OrderDetailsItemArrayAdapter.CartClickListener() {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.orderDetailsBackBtn:
                finish();
                break;
            // Handle other views click
        }
    }
}
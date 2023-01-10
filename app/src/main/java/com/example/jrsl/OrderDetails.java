package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> orderDetails = new ArrayList<>();

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
        OrderDetailsItemArrayAdapter myRecyclerViewAdapter = new OrderDetailsItemArrayAdapter(orderDetails, new OrderDetailsItemArrayAdapter.CartClickListener() {
            @Override
            public void onItemClicked(CartItem orderDetails) {
                Toast.makeText(OrderDetails.this, orderDetails.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindOrderDetailsData()
    {
        orderDetails.add(new CartItem("Darcy Collection","White Dress","M",1, 25, R.drawable.outfit2));
    }
}
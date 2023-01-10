package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<OrderDetailsItem> orderHistoryDetails = new ArrayList<>();

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderHistory.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        OrderHistoryItemArrayAdapter myRecyclerViewAdapter = new OrderHistoryItemArrayAdapter(orderHistoryDetails, new OrderHistoryItemArrayAdapter.CartClickListener() {
            @Override
            public void onItemClicked(OrderDetailsItem orderDetails) {
                Toast.makeText(OrderHistory.this, orderDetails.getOrderRef(), Toast.LENGTH_SHORT).show();
            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindOrderDetailsData()
    {
        orderHistoryDetails.add(new OrderDetailsItem("10/04/2020","D371HS","Delivered",R.drawable.outfit2));
    }
}
package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ArrayList<OrderDetailsItem> orderHistoryDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        bindOrderDetailsData();
        setUIRef();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, OrderDetails.class);
        startActivity(i);
    }

    private void setUIRef() {
        //Reference of RecyclerView
        mRecyclerView = findViewById(R.id.myOrderHistoryList);
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

    private void bindOrderDetailsData() {
        orderHistoryDetails.add(new OrderDetailsItem("10/04/2020", "D371HS", "Delivered", R.drawable.darcy_flaredjumpsuit));
        orderHistoryDetails.add(new OrderDetailsItem("10/04/2020", "D371HS", "Delivered", R.drawable.darcy_flaredjumpsuit));
    }
}


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

public class OrderHistory extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ArrayList<OrderDetailsItem> orderHistoryDetails = new ArrayList<>();


    public OrderHistory() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        bindOrderDetailsData();
        setUIRef();
    }


    private void setUIRef() {
        //Reference of RecyclerView
        mRecyclerView = findViewById(R.id.myOrderHistoryList);
        //Linear Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderHistory.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        OrderHistoryItemArrayAdapter myRecyclerViewAdapter = new OrderHistoryItemArrayAdapter(getApplicationContext(),orderHistoryDetails, new OrderHistoryItemArrayAdapter.CartClickListener() {
            @Override
            public void onItemClicked(OrderDetailsItem orderDetails) {
                Toast.makeText(OrderHistory.this, orderDetails.getOrderRef(), Toast.LENGTH_SHORT).show();
            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindOrderDetailsData() {
        orderHistoryDetails.add(new OrderDetailsItem("10/04/2020", "D371HS", "Delivered", "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630291/ANDE/clothing/lyla_offshouldertop_wy7xb9.jpg"));
        orderHistoryDetails.add(new OrderDetailsItem("10/04/2020", "D371HS", "Delivered", "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630280/ANDE/clothing/freya_pinkdotteddress_dpggft.jpg"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.orderHistoryBackBtn:
                finish();
                break;
            case R.id.orderHistoryNext:
                Intent i1 = new Intent(OrderHistory.this, OrderDetails.class);
                startActivity(i1);
                break;
            case R.id.orderHistoryCart:
                Intent i2 = new Intent(OrderHistory.this, Cart.class);
                startActivity(i2);
                break;
            // Handle other views click
        }
    }
}


package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
                Intent i1 = new Intent(OrderHistory.this, OrderDetails.class);
                Log.d(null, "Total: "+orderDetails.getOrderTotal());
                i1.putExtra("order-ref",orderDetails.getOrderRef());
                i1.putExtra("order-date",orderDetails.getOrderDate());
                i1.putExtra("order-status",orderDetails.getOrderStatus());
                i1.putExtra("order-total",orderDetails.getOrderTotal());
                startActivity(i1);

            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindOrderDetailsData() {

        DatabaseHandler db = new DatabaseHandler(this);
        List<OrderDetailsItem> cart = db.getAllOrderHistory();

        //add cart items
        for (OrderDetailsItem order : cart) {
            orderHistoryDetails.add(new OrderDetailsItem(order.getOrderDate(),order.getOrderRef(),order.getOrderStatus(),order.getOrderImageURL(), order.getOrderTotal()));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.orderHistoryBackBtn:
                finish();
                break;
            case R.id.orderHistoryCart:
                Intent i2 = new Intent(OrderHistory.this, Cart.class);
                startActivity(i2);
                break;
            // Handle other views click
        }
    }
}


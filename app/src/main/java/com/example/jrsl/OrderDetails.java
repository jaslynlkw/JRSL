package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> orderDetails = new ArrayList<>();



    public OrderDetails() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Intent i = getIntent();
        TextView orderDate = findViewById(R.id.orderDateInput);
        orderDate.setText(i.getStringExtra("order-date"));
        TextView orderRef = findViewById(R.id.orderRefInput);
        orderRef.setText(i.getStringExtra("order-ref"));
        TextView orderStatus = findViewById(R.id.orderStatusInput);
        orderStatus.setText(i.getStringExtra("order-status"));
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
                //Toast.makeText(OrderDetails.this, orderDetails.getProduct_id(), Toast.LENGTH_SHORT).show();
               Log.d(null, "product in class : " + orderDetails.getProduct_id());
                Intent i = new Intent(OrderDetails.this, ProductDetails.class);
                i.putExtra("productid_key", String.valueOf(orderDetails.getProduct_id()));
                startActivity(i);
            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindOrderDetailsData()
    {
        Intent i = getIntent();
        String orderRef = i.getStringExtra("order-ref");

        DatabaseHandler db = new DatabaseHandler(this);
        List<CartItem> cart = db.getAllOrderDetails(orderRef);
        TextView totalPrice = findViewById(R.id.orderPriceInput);
        Double price = 0.0;

        //add cart items
        for (CartItem order : cart) {
            orderDetails.add(new CartItem(order.getProduct_id(),order.getCollection(),order.getName(),order.getSize(),order.getQty(),order.getPrice(),order.getImageURL()));
            price+= order.getPrice();
//            Log.d(null, cartitem.getName());
//            Log.d(null,"image url for " + cartitem.getName() + " : " + cartitem.getImageURL());
        }
        //orderDetails.add(new CartItem("Lyla Collection","Off Shoulder Top","M",1, 30.30, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630291/ANDE/clothing/lyla_offshouldertop_wy7xb9.jpg"));
        totalPrice.setText("$"+ price + "0");
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
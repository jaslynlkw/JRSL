package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private ArrayList<ProductItem> productItems = new ArrayList<>();

    public Products() {
        // require a empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        bindProductsData();
        setUIRef();
    }

    private void setUIRef()
    {
        //Reference of RecyclerView
        myRecyclerView = findViewById(R.id.myProductList);
        //Linear Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Products.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        myRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        ProductItemArrayAdapter myRecyclerViewAdapter = new ProductItemArrayAdapter(productItems, new ProductItemArrayAdapter.MyRecyclerViewItemClickListener()
        {
            //Handling clicks
            @Override
            public void onItemClicked(ProductItem products)
            {
                //get item id
            }
        });

        //Set adapter to RecyclerView
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindProductsData()
    {
        DatabaseHandler db = new DatabaseHandler(this);
        List<ProductItem> products = db.getAllProducts();

        //add products
        for (ProductItem product : products) {
            productItems.add(new ProductItem(product.getProductID(), product.getCollection(), product.getName(), product.getPrice(), product.getImage(), product.getSavedStatus()));
        }
    }


}
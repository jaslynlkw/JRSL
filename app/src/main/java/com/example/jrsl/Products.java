package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity implements View.OnClickListener {
   // private final Context context;
    private RecyclerView myRecyclerView;
    private ArrayList<ProductItem> productItems = new ArrayList<>();

//    public Products(Context context) {
//        this.context = context;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category_key");

        bindProductsData(category);
        setUIRef();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // redirection back to Search Categories page
            case R.id.textViewProductCategory:
                finish();
                break;
        }
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
        ProductItemArrayAdapter myRecyclerViewAdapter = new ProductItemArrayAdapter(getApplicationContext(),productItems, new ProductItemArrayAdapter.MyRecyclerViewItemClickListener()
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

    private void bindProductsData(String category)
    {
        DatabaseHandler db = new DatabaseHandler(this);
        List<ProductItem> products = db.getAllProducts(category);

        //add product items
        for (ProductItem product : products) {
            productItems.add(new ProductItem(product.getProductID(), product.getCollection(), product.getName(), product.getPrice(), product.getImageURL(), product.getCategory(), product.getSavedStatus()));
            Log.d(null, product.getName());
        }
    }


}
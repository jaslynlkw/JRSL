package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity implements View.OnClickListener, ProductItemArrayAdapter.MyRecyclerViewItemClickListener {
    private RecyclerView myRecyclerView;
    private ArrayList<ProductItem> productItems = new ArrayList<>();
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category_key");

        //set back button text
        TextView categoryText = findViewById(R.id.textViewProductCategory);
        categoryText.setText("All " + category);

        //set product count
        TextView productsCountText = findViewById(R.id.productsCountText);
        productsCountText.setText(db.getProductsCount(category) + " items");

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
        GridLayoutManager linearLayoutManager = new GridLayoutManager(Products.this, 2);
        //Set Layout Manager to RecyclerView
        myRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        ProductItemArrayAdapter myRecyclerViewAdapter = new ProductItemArrayAdapter(getApplicationContext(),productItems,this);


        //Set adapter to RecyclerView
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindProductsData(String category)
    {
        List<ProductItem> products = db.getAllProducts(category);

        //add product items
        for (ProductItem product : products) {
            productItems.add(new ProductItem(product.getProductID(), product.getCollection(), product.getName(), product.getPrice(), product.getImageURL(), product.getCategory(), product.getSavedStatus()));
            Log.d(null, product.getName());
            Log.d(null,"image url for " + product.getName() + " : " + product.getImageURL());
        }
    }


    @Override
    public void onItemClicked(int position) {
        ProductItem product = productItems.get(position);
        Log.d(null, "UAGFOHDABOHDA");
        //upon item being clicked, do smth
        Intent i = new Intent(Products.this, ProductDetails.class);
        startActivity(i);
        i.putExtra("productid_key", product.getProductID());
    }
}
package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView myRecyclerView;
    private ArrayList<ProductItem> productItems = new ArrayList<>();

    public Products() {
        // require a empty public constructor
    }

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

    private void bindProductsData(String category)
    {

        //populate Products database
        productItems.add(new ProductItem(1,"Lyla Collection","Off Shoulder Top","Elegant White Off Shoulder Top with Frills. Appropriate for casual to semi-formal events.",30.30,R.drawable.lyla_offshouldertop,"clothing", 0));
        productItems.add(new ProductItem(2,"Freya Collection","Pink Dotted Dress","Cute Pink Dotted Dress with Frills. Appropriate for casual gatherings.",60.40,R.drawable.freya_pinkdotteddress,"clothing", 0));
        productItems.add(new ProductItem(3,"Aleya Collection","Extravagant Black Dress","Elegant Chic Black Dress with Frills. Appropriate for big events.",230.30,R.drawable.aleya_extravagantblackdress,"clothing", 0));
        productItems.add(new ProductItem(4,"Daniel Collection","White Flared Jeans","Cool white flared jeans for cool times. Appropriate for casual to semi-formal events.",45.60,R.drawable.daniel_whiteflaredjeans,"clothing", 0));
        productItems.add(new ProductItem(5,"Reza Collection","Off-white Teddy Bear Coat","Oversized Teddy Bear Coat for the chilly days. Appropriate for casual to semi-formal events.",80.90,R.drawable.reza_offwhiteteddybearcoat,"clothing", 0));
        productItems.add(new ProductItem(6,"Niki Collection","Yellow Matching Set","Yellow hoodie and sweatpants set. Appropriate for casual events.",71.20,R.drawable.niki_yellowmatchingset,"clothing", 0));

        productItems.add(new ProductItem(7,"Camila Collection","Canvas High Top Shoes","Black High Top shoes made of canvas. Appropriate for casual events.",70.00,R.drawable.camila_canvashightopshoes,"shoes", 0));
        productItems.add(new ProductItem(8,"Allanwood Collection","Pink Gradient Sparkly Heels","Elegant and eye-catching pink sparkly heels. Appropriate for casual gatherings and big events.",160.00,R.drawable.allanwood_sparklyheels,"shoes", 0));
        productItems.add(new ProductItem(9,"Nathan Collection","Brown Boots","Brown waterproof boots. Appropriate for snowy weather.",130.00,R.drawable.nathan_brownboots,"shoes", 0));
        productItems.add(new ProductItem(10,"Laura Collection","Black Court Shoes","Classic black court shoes. Appropriate for casual to formal events.",45.00,R.drawable.laura_blackcourtshoes,"shoes", 0));
        productItems.add(new ProductItem(11,"Erik Collection","Salmon Pink Sneakers","Comfy sneakers. And they're pink! Appropriate for casual to semi-formal events.",90.00,R.drawable.reza_offwhiteteddybearcoat,"shoes", 0));
        productItems.add(new ProductItem(12,"Najla Collection","Heeled Ballet Flats","Simple and classic ballet flats, with a bit of a heel! Appropriate for casual to formal events.",40.00,R.drawable.najla_heeledballetflats,"shoes", 0));

        DatabaseHandler db = new DatabaseHandler(this);
        List<ProductItem> products = db.getAllProducts(category);
    }


}
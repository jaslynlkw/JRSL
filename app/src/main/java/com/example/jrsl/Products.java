package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity implements View.OnClickListener {
    private final Context context;
    private RecyclerView myRecyclerView;
    private ArrayList<ProductItem> productItems = new ArrayList<>();

    public Products(Context context) {
        // require a empty public constructor
        this.context = context;
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
        ProductItemArrayAdapter myRecyclerViewAdapter = new ProductItemArrayAdapter(context,productItems, new ProductItemArrayAdapter.MyRecyclerViewItemClickListener()
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
        productItems.add(new ProductItem(1,"Lyla Collection","Off Shoulder Top","Elegant White Off Shoulder Top with Frills. Appropriate for casual to semi-formal events.",30.30,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630291/ANDE/clothing/lyla_offshouldertop_wy7xb9.jpg","clothing", 0));
        productItems.add(new ProductItem(2,"Freya Collection","Pink Dotted Dress","Cute Pink Dotted Dress with Frills. Appropriate for casual gatherings.",60.40,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630280/ANDE/clothing/freya_pinkdotteddress_dpggft.jpg","clothing", 0));
        productItems.add(new ProductItem(3,"Aleya Collection","Extravagant Black Dress","Elegant Chic Black Dress with Frills. Appropriate for big events.",230.30,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630258/ANDE/clothing/aleya_extravagantblackdress_c96drc.jpg","clothing", 0));
        productItems.add(new ProductItem(4,"Daniel Collection","White Flared Jeans","Cool white flared jeans for cool times. Appropriate for casual to semi-formal events.",45.60,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630272/ANDE/clothing/daniel_whiteflaredjeans_osrtq6.jpg","clothing", 0));
        productItems.add(new ProductItem(5,"Reza Collection","Off-white Teddy Bear Coat","Oversized Teddy Bear Coat for the chilly days. Appropriate for casual to semi-formal events.",80.90,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630313/ANDE/clothing/reza_offwhiteteddybearcoat_ctx2ui.jpg","clothing", 0));
        productItems.add(new ProductItem(6,"Niki Collection","Yellow Matching Set","Yellow hoodie and sweatpants set. Appropriate for casual events.",71.20,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630312/ANDE/clothing/niki_yellowmatchingset_snyyyk.jpg","clothing", 0));

        productItems.add(new ProductItem(7,"Camila Collection","Canvas High Top Shoes","Black High Top shoes made of canvas. Appropriate for casual events.",70.00,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630336/ANDE/shoes/camila_canvashightopshoes_qvl0if.jpg","shoes", 0));
        productItems.add(new ProductItem(8,"Allanwood Collection","Pink Gradient Sparkly Heels","Elegant and eye-catching pink sparkly heels. Appropriate for casual gatherings and big events.",160.00,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630337/ANDE/shoes/allanwood_sparklyheels_etuyz2.jpg","shoes", 0));
        productItems.add(new ProductItem(9,"Nathan Collection","Brown Boots","Brown waterproof boots. Appropriate for snowy weather.",130.00,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630354/ANDE/shoes/nathan_brownboots_cc6ipo.jpg","shoes", 0));
        productItems.add(new ProductItem(10,"Laura Collection","Black Court Shoes","Classic black court shoes. Appropriate for casual to formal events.",45.00,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630348/ANDE/shoes/laura_blackcourtshoes_yehhgs.jpg","shoes", 0));
        productItems.add(new ProductItem(11,"Erik Collection","Salmon Pink Sneakers","Comfy sneakers. And they're pink! Appropriate for casual to semi-formal events.",90.00,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630342/ANDE/shoes/erik_salmonpinksneakers_muizqj.jpg","shoes", 0));
        productItems.add(new ProductItem(12,"Najla Collection","Heeled Ballet Flats","Simple and classic ballet flats, with a bit of a heel! Appropriate for casual to formal events.",40.00,"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630355/ANDE/shoes/najla_heeledballetflats_gid0rz.jpg","shoes", 0));

        DatabaseHandler db = new DatabaseHandler(this);
        List<ProductItem> products = db.getAllProducts(category);
    }


}
package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ProductDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        int productID = Integer.parseInt(intent.getStringExtra("productid_key"));
        DatabaseHandler db = new DatabaseHandler(this);
        ProductItem product = db.getProduct(productID);
        Log.d(null, "PRODUCT DETAIL NAME: " + product.getName());
    }

}
package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SearchCategories extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_categories);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchCatCart:
                Log.d(null,"I am here in cart :> search cat");
                Intent i = new Intent(SearchCategories.this, Cart.class);
                startActivity(i);
                break;
            case R.id.arrowBackSearch:
                finish();
                break;
            case R.id.ClothingOption:
                Intent i1 = new Intent(SearchCategories.this, Products.class);
                i1.putExtra("category_key", "clothing");
                startActivity(i1);
                break;
            case R.id.ShoesOption:
                Intent i2 = new Intent(SearchCategories.this, Products.class);
                i2.putExtra("category_key", "shoes");
                startActivity(i2);
                break;
        }
    }

}
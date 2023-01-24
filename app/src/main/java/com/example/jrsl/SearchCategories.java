package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SearchCategories extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_categories);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchCart:
                Intent i = new Intent(this, Cart.class);
                startActivity(i);
                break;
            case R.id.arrowBackSearch:
                finish();
                break;
            case R.id.ClothingOption:
                Intent i1 = new Intent(this, Products.class);
                i1.putExtra("category_key", "clothing");
                startActivity(i1);
                break;
            default:
                // Handle other views click
        }
    }

}
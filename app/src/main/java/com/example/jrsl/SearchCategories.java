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
        Intent i = new Intent(this, Products.class);
        startActivity(i);
    }

}
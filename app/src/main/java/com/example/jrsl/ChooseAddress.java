package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseAddress extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(ChooseAddress.this, Payment.class);
        startActivity(i);
    }
}
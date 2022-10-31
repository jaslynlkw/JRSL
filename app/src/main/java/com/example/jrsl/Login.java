package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            // upon clicking login btn, redirected to home page
            case R.id.btnLogin:
                Intent i = new Intent(this, Home.class);
                startActivity(i);
                break;
        }
    }

}
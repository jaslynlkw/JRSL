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
            case R.id.btnToLogin:
                Intent i1 = new Intent(this, Home.class);
                startActivity(i1);
                break;

            // upon clicking sign up btn, redirected to sign up page
            case R.id.btnToSignUp:
                Intent i2 = new Intent(this, SignUp.class);
                startActivity(i2);
                break;
        }
    }

}
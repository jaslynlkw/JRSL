package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddressSignUp extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_sign_up);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // redirection from address (sign up) to login
            case R.id.btnBacktoLogin:
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                break;
            // redirection back to Sign Up page
            case R.id.backToSignUpBtn:
                Intent i2 = new Intent(this, SignUp.class);
                startActivity(i2);
                break;
        }
    }
}
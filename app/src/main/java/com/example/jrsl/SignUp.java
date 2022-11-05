package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // redirection from sign up to address (sign up)
            case R.id.btnSignUp:
                Intent i = new Intent(this, AddressSignUp.class);
                startActivity(i);
                break;
        }
    }


}
package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText edUsername;
    EditText edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.inputUsername);
        edPassword = findViewById(R.id.inputPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // upon clicking login btn, redirected to home page
            case R.id.btnToLogin:

                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                Log.d(null, "username + password = " + username + password);

                DatabaseHandler db = new DatabaseHandler(this);
                if (db.validateUser(username, password)) {
                    Toast.makeText(Login.this, "Logging in...", Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(this, MainActivity.class);
                    startActivity(i1);
                } else {
                    Toast.makeText(Login.this, "Unable to Login", Toast.LENGTH_SHORT).show();
                }
                break;

            // upon clicking sign up btn, redirected to sign up page
            case R.id.btnToSignUp:
                Intent i2 = new Intent(this, SignUp.class);
                startActivity(i2);
                break;
        }
    }

}
package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //sharedPrefs
    SharedPreferences sharedPref;
    public static final String userIDKey = "userID";
    public static final String usernameKey = "userName";
    public static final String userEmailKey = "userEmail";
    public static final String userAddressKey = "userAddress";
    public static final String userSavedItemsKey = "userSavedItems";
    public static final String userPrefs = "UserPref";

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

                //get input username and password
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                //ensure that username and password fields are not empty
                if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please input username and password.", Toast.LENGTH_SHORT).show();
                    break;
                }

                //validate user
                DatabaseHandler db = new DatabaseHandler(this);
                String[] userResults = db.validateUser(username, password);
                if (userResults[0] == "true") {

                    //loading preferences + adding values
                    sharedPref = getSharedPreferences(userPrefs, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(userIDKey, Integer.parseInt(userResults[1]));
                    editor.putString(usernameKey, userResults[2]);
                    editor.putString(userEmailKey, userResults[3]);
                    editor.putString(userAddressKey, userResults[4]);
                    editor.putString(userSavedItemsKey, userResults[5]);
                    editor.commit();

                    //redirect to home page
                    Toast.makeText(Login.this, "Logging in...", Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(this, MainActivity.class);
                    startActivity(i1);

                    edUsername.getText().clear();
                    edPassword.getText().clear();

                } else {
                    Toast.makeText(Login.this, "Unable to Login", Toast.LENGTH_SHORT).show();
                }
                break;

            // upon clicking sign up btn, redirected to sign up page
            case R.id.btnToSignUp:
                Intent i2 = new Intent(this, SignUp.class);
                startActivity(i2);
                break;

            // upon clicking show hide btn, show/hide password
            case R.id.showHideBtn:
                ImageView eyeIcon = v.findViewById(R.id.showHideBtn);

                if (edPassword.getTransformationMethod().getClass().getSimpleName() .equals("PasswordTransformationMethod")) {
                    //show hidden password
                    edPassword.setTransformationMethod(new SingleLineTransformationMethod());
                    eyeIcon.setImageResource(R.drawable.hide_password);

                } else {
                    //hide password
                    edPassword.setTransformationMethod(new PasswordTransformationMethod());
                    eyeIcon.setImageResource(R.drawable.show_password);

                }
                break;
        }
    }

}
package com.example.jrsl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class Payment extends AppCompatActivity{
    SharedPreferences sharedPref;

    SharedPreferences sharedPref1;
    public static final String userPrefs = "UserPref";

    public static final String userIDKey = "userID";

    public static final String userAddressKey = "userAddress";
    public static final String cartPrefs = "CartPref";
    public static final String cartProductIds = "cartProdIds";
    public static final String cartSizes = "cartSizes";
    public static final String cartPrices = "cartPrices";
    public static final String cartQty = "cartQty";
    public static final String cartSubTotal = "cartSubTotal";
    private String firstName;
    private String lastName;
    AlertDialog.Builder alertBuilder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent i = getIntent();
        firstName = i.getStringExtra("first-name");
        lastName = i.getStringExtra("last-name");
        TextView firstNameText = findViewById(R.id.inputPaymentFirstName);
        firstNameText.setText(firstName);
        TextView lastNameText = findViewById(R.id.inputPaymentLastName);
        lastNameText.setText(lastName);
        CardForm cardForm = findViewById(R.id.card_form);
        Button buy = findViewById(R.id.btnPlaceOrder);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(false)
                .mobileNumberRequired(false)
                .mobileNumberExplanation("nthn happens")
                .setup(Payment.this);


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    sharedPref = getSharedPreferences(cartPrefs, MODE_PRIVATE);
                    String orderProductIds = sharedPref.getString(cartProductIds, null);
                    String orderSizes = sharedPref.getString(cartSizes, null);
                    String orderPrices = sharedPref.getString(cartPrices, null);
                    String orderQty = sharedPref.getString(cartQty, null);
                    String orderSubtotal = sharedPref.getString(cartSubTotal, null);
                    double finalOrderTotal = Double.parseDouble(orderSubtotal);
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    String today = day + "/" + month + "/" + year;
                    sharedPref1 = getSharedPreferences(userPrefs, MODE_PRIVATE);
                    int userId = sharedPref1.getInt(userIDKey, 1);
                    String userAddress = sharedPref1.getString(userAddressKey, null);
                    int length = 6;
                    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                    Random rand = new SecureRandom();
                    char[] text = new char[length];
                    for (int i = 0; i < length; i++) {
                        text[i] = characters.charAt(rand.nextInt(characters.length()));
                    }
                    String orderRef = new String(text);
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                     db.addToOrder(userId,userAddress,orderProductIds,orderSizes,orderQty,orderPrices,finalOrderTotal,"Delivered",today,orderRef);
                     Log.d(null,"Gonna delete cart");
                     db.deleteAllFromCart();
                     Intent i = new Intent(Payment.this, Receipt.class);
                    startActivity(i);

                } else {
                    Toast.makeText(Payment.this, "Please complete the form", Toast.LENGTH_LONG).show();
                }
            }
        });

}

    }


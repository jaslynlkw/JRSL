package com.example.jrsl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;


public class Payment extends AppCompatActivity{
    AlertDialog.Builder alertBuilder;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
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
                    Intent i = new Intent(Payment.this, Receipt.class);
                    startActivity(i);

                } else {
                    Toast.makeText(Payment.this, "Please complete the form", Toast.LENGTH_LONG).show();
                }
            }
        });

}

    }


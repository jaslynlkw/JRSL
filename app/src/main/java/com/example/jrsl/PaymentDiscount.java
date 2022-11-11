package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaymentDiscount extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_discount);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // redirection from address (payment) to discount (payment)
            case R.id.btnPaymentAddress:
                Intent i = new Intent(this, Payment.class);
                startActivity(i);
                break;
        }
    }
}
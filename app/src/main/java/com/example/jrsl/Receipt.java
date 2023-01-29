package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Receipt extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        //get receipt details
        DatabaseHandler db = new DatabaseHandler(this);
        OrderDetailsItem receipt = db.getReceipt();

        //setting receipt details
        TextView orderRefText = findViewById(R.id.receiptOrderRefInput);
        orderRefText.setText(receipt.getOrderRef());

        TextView orderDateText = findViewById(R.id.receiptOrderDateInput);
        orderDateText.setText(receipt.getOrderDate());

        TextView orderAddressText = findViewById(R.id.receiptOrderRefInput);
        orderRefText.setText(receipt.getOrderRef());

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // redirection back to Home page
            case R.id.btnBackToHome:
                Intent i = new Intent(this, Home.class);
                break;

        }
    }
}
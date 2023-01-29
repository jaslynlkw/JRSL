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

import java.util.ArrayList;
import java.util.Arrays;

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

        String addressStr = receipt.getOrderAddress();
        ArrayList<String> addressArrayList = new ArrayList<>(Arrays.asList(addressStr.split(",")));

        TextView orderAddressStreetText = findViewById(R.id.receiptOrderAddressStreet);
        orderAddressStreetText.setText(addressArrayList.get(3));

        TextView orderAddressStreet2Text = findViewById(R.id.receiptOrderAddressStreet2);
        orderAddressStreet2Text.setText(addressArrayList.get(4));

        TextView orderAddressPostalCodeText = findViewById(R.id.receiptOrderAddressPostalCode);
        orderAddressPostalCodeText.setText(addressArrayList.get(5));

        TextView orderAddressCityText = findViewById(R.id.receiptOrderAddressCity);
        orderAddressCityText.setText(addressArrayList.get(2));

        TextView orderAddressCountryText = findViewById(R.id.receiptOrderAddressCountry);
        orderAddressCountryText.setText(addressArrayList.get(6));

        TextView ordertotalText = findViewById(R.id.receiptOrdertotalInput);
        ordertotalText.setText("$ "+receipt.getOrderTotal()+"0");

        TextView orderShippingText = findViewById(R.id.receiptOrderShippingInput);
        orderShippingText.setText("$ "+receipt.getShipping()+"0");

        TextView orderSubTotalText = findViewById(R.id.receiptSubTotalInput);
        orderSubTotalText.setText("$ "+String.valueOf(receipt.getOrderTotal() + receipt.getShipping())+"0");

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // redirection back to Home page
            case R.id.btnBackToHome:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

        }
    }
}
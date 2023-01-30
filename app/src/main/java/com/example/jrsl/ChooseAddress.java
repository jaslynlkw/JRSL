package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChooseAddress extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sharedPref;
    public static final String userPrefs = "UserPref";
    public static final String userAddressKey = "userAddress";
    private String firstName;
    private String lastName;
    private String country;
    private String streetName;
    private String houseNo;
    private String postalCode;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
        sharedPref = getSharedPreferences(userPrefs, MODE_PRIVATE);
        String address = sharedPref.getString(userAddressKey, null);
        String [] addressArr = address.split(",");
        firstName = addressArr[0];
        lastName =  addressArr[1];
        country =  addressArr[2];
        streetName =  addressArr[3];
        houseNo =  addressArr[4];
        postalCode =  addressArr[5];
        city =  addressArr[6];

        TextView nameText = findViewById(R.id.addressUserName);
        nameText.setText(firstName+"  "+lastName);
        TextView streetNameText = findViewById(R.id.addressUserStreetName);
        streetNameText.setText(streetName);
        TextView unitNumText = findViewById(R.id.addressUserUnitNum);
        unitNumText.setText(houseNo);
        TextView postalCodeText = findViewById(R.id.addressUserPostalCode);
        postalCodeText.setText(city+"  "+postalCode);
        TextView postalNumText = findViewById(R.id.addressUserPostalNum);
        postalNumText.setText(postalCode);
        TextView cityText = findViewById(R.id.addressUserCity);
        cityText.setText(city);
        TextView countryText = findViewById(R.id.addressUserCountry);
        countryText.setText(country);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(ChooseAddress.this, Payment.class);
        i.putExtra("first-name", firstName);
        i.putExtra("last-name", lastName);
        startActivity(i);
    }
}
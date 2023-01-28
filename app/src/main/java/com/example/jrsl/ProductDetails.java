package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private int productID;
    private Spinner spinner;
    private static final Integer[] qty = {1,2,3,4,5,6,7,8,9,10};
    private RadioGroup radioSizeGroup;
    private RadioButton radioSizeButton;
    private int sizeSelected;
    private int qtySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //getting product
        Intent intent = getIntent();
        Log.d(null,"DOES THIS WORK : " + intent.getStringExtra("productid_key"));
        String id = intent.getStringExtra("productid_key");
        productID = Integer.parseInt(id);
        DatabaseHandler db = new DatabaseHandler(this);
        ProductItem product = db.getProduct(productID);
        Log.d(null, "PRODUCT DETAIL NAME: " + product.getName());

        //setting product details
        ImageView productImage = findViewById(R.id.productDetailImage);
//        productImage.setImageResource(?????);

        TextView collectionText = findViewById(R.id.productDetailCollection);
        collectionText.setText(product.getCollection());

        TextView nameText = findViewById(R.id.productDetailName);
        nameText.setText(product.getName());

        TextView priceText = findViewById(R.id.productDetailPrice);
        priceText.setText(String.valueOf(product.getPrice()));

        //setting qty spinner
        spinner = (Spinner)findViewById(R.id.productQtySpinner);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(ProductDetails.this,
                android.R.layout.simple_spinner_item, qty);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //setting saved icon
        SharedPreferences pref = getSharedPreferences("UserPref", MODE_PRIVATE);
        String savedItems = pref.getString("userSavedItems", null);
        String[] savedItemsArr = savedItems.split(",");
        String productIDStr = String.valueOf(productID);
        for(int index=0; index<savedItemsArr.length; index++) {
            if (savedItemsArr[index].equals(productIDStr)) {
                ImageView savedIcon = findViewById(R.id.imageViewSaved);
                savedIcon.setImageResource(R.drawable.icon_saved);
                break;
            }
        }
        //radio group
        radioSizeGroup=(RadioGroup)findViewById(R.id.productSizeRadioGroup);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        qtySelected = (Integer)adapterView.getItemAtPosition(position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // add product to cart
            case R.id.btnAddtoCart:

                // Get the selected size
                int selectedId=radioSizeGroup.getCheckedRadioButtonId();
                radioSizeButton=(RadioButton)findViewById(selectedId);
                sizeSelected = Integer.parseInt(radioSizeButton.getText().toString());

                DatabaseHandler db = new DatabaseHandler(this);
//                boolean insertResults = db.addToCart(productID, sizeSelected, qtySelected, price, 0);
//
//                if (insertResults) {
//                    Toast.makeText(ProductDetails.this, "Product successfully added to cart.", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(ProductDetails.this, "Product failed to be added to cart.", Toast.LENGTH_SHORT).show();
//                }

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
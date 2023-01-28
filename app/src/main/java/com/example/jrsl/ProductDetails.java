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

import com.bumptech.glide.Glide;

public class ProductDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private int productID;
    private Spinner spinner;
    private static final Integer[] qty = {1,2,3,4,5,6,7,8,9,10};
    private RadioGroup radioSizeGroup;
    private RadioButton radioSizeButton;
    private String sizeSelected;
    private int qtySelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //getting product
        Intent intent = getIntent();
        String id = intent.getStringExtra("productid_key");
        Log.d(null, "PRODUCT DETAIL ID: " + id);
        productID = Integer.parseInt(id);
        DatabaseHandler db = new DatabaseHandler(this);
        ProductItem product = db.getProduct(productID);
        Log.d(null, "PRODUCT DETAIL NAME: " + product.getName());

        //setting product details
        ImageView productImage = findViewById(R.id.productDetailImage);
        Glide.with(getApplicationContext())
                .load(product.getImageURL())
                .into(productImage);

        TextView collectionText = findViewById(R.id.productDetailCollection);
        collectionText.setText(product.getCollection());

        TextView nameText = findViewById(R.id.productDetailName);
        nameText.setText(product.getName());

        TextView priceText = findViewById(R.id.productDetailPrice);
        priceText.setText("$" + product.getPrice() + "0");

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
        DatabaseHandler db = new DatabaseHandler(this);
        ProductItem product = db.getProduct(productID);
        switch (view.getId()) {
            // add product to cart
            case R.id.btnAddtoCart:

                // Get the selected size
                int selectedId=radioSizeGroup.getCheckedRadioButtonId();
                radioSizeButton=(RadioButton)findViewById(selectedId);
                sizeSelected = radioSizeButton.getText().toString();

                // Get price
                double price = qtySelected * product.getPrice();

                db.addToCart(productID, sizeSelected, qtySelected, price, 0.00);
                Toast.makeText(ProductDetails.this, "Product successfully added to cart.", Toast.LENGTH_SHORT).show();

                break;
            // cart icon
            case R.id.imageViewCart:
                Intent i = new Intent(this, Cart.class);
                startActivity(i);
                break;
            // back to products
            case R.id.backArrowBtn:
                finish();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
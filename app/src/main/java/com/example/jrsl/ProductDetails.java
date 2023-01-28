package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
        productID = Integer.parseInt(intent.getStringExtra("productid_key"));
        DatabaseHandler db = new DatabaseHandler(this);
        ProductItem product = db.getProduct(productID);
        Log.d(null, "PRODUCT DETAIL NAME: " + product.getName());

        //setting qty spinner
        spinner = (Spinner)findViewById(R.id.productQtySpinner);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(ProductDetails.this,
                android.R.layout.simple_spinner_item, qty);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
            case R.id.btnAddtoCart:

                // Get the selected size
                int selectedId=radioSizeGroup.getCheckedRadioButtonId();
                radioSizeButton=(RadioButton)findViewById(selectedId);
                sizeSelected = Integer.parseInt(radioSizeButton.getText().toString());

                DatabaseHandler db = new DatabaseHandler(this);
                db.addToCart(productID, sizeSelected, qtySelected);

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
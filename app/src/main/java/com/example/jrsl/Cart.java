package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity implements View.OnClickListener, CartItemArrayAdapter.CartClickListener {

    SharedPreferences sharedPref;
    public static final String cartPrefs = "CartPref";
    public static final String cartProductIds = "cartProdIds";
    public static final String cartSizes = "cartSizes";
    public static final String cartPrices = "cartPrices";
    public static final String cartQty = "cartQty";
    public static final String cartSubTotal = "cartSubTotal";
    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    private ArrayList<String> productid = new ArrayList<>();

    private String productid_pref ;

    private ArrayList<String> sizes = new ArrayList<>();

    private String sizes_pref ;

    private ArrayList<String> prices = new ArrayList<>();

    private String prices_pref ;

    private ArrayList<String> qty = new ArrayList<>();

    private String qty_pref ;

    private double totalPrice;

    public Cart() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bindCartData();
        setUIRef();
        productid_pref = String.join(", ",productid);
        sizes_pref = String.join(",",sizes);
        prices_pref = String.join(", ",prices);
        qty_pref = String.join(", ",qty);

        if (cartItems.toArray().length == 0) {
            TextView nocartitemstext = findViewById(R.id.noCartItemsTextView);
            nocartitemstext.setText("*You have no items in your cart currently.");
        }
    }
    private void setUIRef()
    {
        //Reference of RecyclerView
        mRecyclerView = findViewById(R.id.myCartList);
        //Linear Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Cart.this, RecyclerView.VERTICAL, false);
        //Set Layout Manager to RecyclerView
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        CartItemArrayAdapter myRecyclerViewAdapter = new CartItemArrayAdapter(getApplicationContext(),cartItems,this);

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindCartData()
    {
        cartItems.clear();
        DatabaseHandler db = new DatabaseHandler(this);
        List<CartItem> cart = db.getAllCartItems();

        //add cart items
        for (CartItem cartitem : cart) {

            cartItems.add(new CartItem(cartitem.getProduct_id(),cartitem.getCollection(),cartitem.getName(),cartitem.getSize(),cartitem.getQty(),Double.parseDouble(String.format("%.2f", cartitem.getPrice())),cartitem.getImageURL()));
            productid.add(String.valueOf(cartitem.getProduct_id()));
            sizes.add(cartitem.getSize());
            prices.add(String.valueOf(cartitem.getPrice()));
            qty.add(String.valueOf(cartitem.getQty()));
            totalPrice+= cartitem.getPrice();
            Log.d(null, cartitem.getName());
            Log.d(null,"image url for " + cartitem.getName() + " : " + cartitem.getImageURL());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BackArrow:
                finish();
                break;
            case R.id.btnPlaceOrder:
                DecimalFormat df = new DecimalFormat("#.##");
                double roundedtotal = Double.valueOf(df.format(totalPrice));
                sharedPref = getSharedPreferences(cartPrefs, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(cartProductIds,productid_pref);
                editor.putString(cartSizes, sizes_pref);
                editor.putString(cartPrices, prices_pref);
                editor.putString(cartQty, qty_pref);
                editor.putString(cartSubTotal, String.valueOf(roundedtotal));
                editor.commit();

                Intent i1 = new Intent(Cart.this, ChooseAddress.class);
                startActivity(i1);
                break;
                // Handle other views click
        }

    }

    @Override
    public void onItemClicked(int position) {

        CartItem cart = cartItems.get(position);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        Toast.makeText(Cart.this, cart.getName() + " was successfully removed from cart", Toast.LENGTH_SHORT).show();
        db.deleteOneFromCart(cart.getProduct_id());

        bindCartData();
        setUIRef();

    }


}
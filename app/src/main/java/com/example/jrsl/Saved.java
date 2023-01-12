package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Saved extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    public Saved() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_saved, container, false);
        setUIRef(view);
        bindSavedData();
        return view;


    }

    private void setUIRef(View view) {
        //Reference of RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.myCartList);
        //Linear Layout Manager
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);
        //Set Layout Manager to RecyclerView
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        CartItemArrayAdapter myRecyclerViewAdapter = new CartItemArrayAdapter(cartItems, new CartItemArrayAdapter.CartClickListener() {
            @Override
            public void onItemClicked(CartItem cartItem) {
                Toast.makeText(getActivity(), cartItem.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });

        //Set adapter to RecyclerView
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private void bindSavedData() {
        cartItems.add(new CartItem("Darcy Collection", "White Dress", 25, R.drawable.outfit2));
        cartItems.add(new CartItem("Cancy Collection", "Brown Coat", 65, R.drawable.outfit3));
    }
}
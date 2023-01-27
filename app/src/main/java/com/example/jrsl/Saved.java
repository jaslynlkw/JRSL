package com.example.jrsl;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Saved extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private Context context;

    public Saved() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        View view = inflater.inflate(R.layout.activity_saved, container, false);
        setUIRef(view);
        bindSavedData();
        view.findViewById(R.id.savedCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.savedCart:
                        Intent i1 = new Intent(getActivity(), Cart.class);
                        startActivity(i1);
                        break;
                    // Handle other views click
                }
            }
        });
        return view;
    }

    private void setUIRef(View view) {
        //Reference of RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mySavedList);
        //Linear Layout Manager
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);
        //Set Layout Manager to RecyclerView
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Create adapter
        SavedItemArrayAdapter myRecyclerViewAdapter = new SavedItemArrayAdapter(context ,cartItems, new SavedItemArrayAdapter.SavedClickListener() {
            @Override
            public void onItemClicked(CartItem savedItem) {

            }
        });

                //Set adapter to RecyclerView
                mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private Saved getContext(Saved saved) {
        return Saved.this;
    }

    private void bindSavedData() {
        cartItems.add(new CartItem("Reza Collection", "Off-white Teddy Bear Coat", 80.90, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630313/ANDE/clothing/reza_offwhiteteddybearcoat_ctx2ui.jpg"));
        cartItems.add(new CartItem("Niki Collection", "Yellow Matching Set", 71.20, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630312/ANDE/clothing/niki_yellowmatchingset_snyyyk.jpg"));
        cartItems.add(new CartItem("Reza Collection", "Off-white Teddy Bear Coat", 80.90, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630313/ANDE/clothing/reza_offwhiteteddybearcoat_ctx2ui.jpg"));
        cartItems.add(new CartItem("Niki Collection", "Yellow Matching Set", 71.20, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630312/ANDE/clothing/niki_yellowmatchingset_snyyyk.jpg"));
        cartItems.add(new CartItem("Reza Collection", "Off-white Teddy Bear Coat", 80.90, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630313/ANDE/clothing/reza_offwhiteteddybearcoat_ctx2ui.jpg"));
        cartItems.add(new CartItem("Niki Collection", "Yellow Matching Set", 71.20, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630312/ANDE/clothing/niki_yellowmatchingset_snyyyk.jpg"));
        cartItems.add(new CartItem("Reza Collection", "Off-white Teddy Bear Coat", 80.90, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630313/ANDE/clothing/reza_offwhiteteddybearcoat_ctx2ui.jpg"));
        cartItems.add(new CartItem("Niki Collection", "Yellow Matching Set", 71.20, "https://res.cloudinary.com/jaslynlkw/image/upload/v1674630312/ANDE/clothing/niki_yellowmatchingset_snyyyk.jpg"));
    }
}
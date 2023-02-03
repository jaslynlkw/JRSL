package com.example.jrsl;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Saved extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<ProductItem> savedItems = new ArrayList<>();
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
        SavedItemArrayAdapter myRecyclerViewAdapter = new SavedItemArrayAdapter(context ,savedItems, new SavedItemArrayAdapter.SavedClickListener() {
            @Override
            public void onItemClicked(ProductItem savedItem) {
                String prodId = String.valueOf(savedItem.getProductID());
                Intent i = new Intent(getContext(), ProductDetails.class);
                i.putExtra("productid_key", prodId);
                startActivity(i);
            }
        });

                //Set adapter to RecyclerView
                mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private Saved getContext(Saved saved) {
        return Saved.this;
    }

    private void bindSavedData() {

        savedItems.clear();
        DatabaseHandler db = new DatabaseHandler(getActivity());
        List<ProductItem> products = db.getAllSavedProducts();

        //add product items
        for (ProductItem saved : products) {
            savedItems.add(new ProductItem(saved.getProductID(), saved.getCollection(), saved.getName(), saved.getPrice(), saved.getImageURL(), saved.getCategory(), saved.getSavedStatus()));
        }

    }
}
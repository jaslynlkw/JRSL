package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Search extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_search, container, false);
        view.findViewById(R.id.CategoriesOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (view.getId()) {
                    case R.id.imageViewCart:
                        Intent i = new Intent(getActivity(), Cart.class);
                        startActivity(i);
                        break;
                    case R.id.CategoriesOption:
                        Intent i1 = new Intent(getActivity(), SearchCategories.class);
                        startActivity(i1);
                        break;
                    default:
                        // Handle other views click
                }

            }
        });
        return view;
    }
}

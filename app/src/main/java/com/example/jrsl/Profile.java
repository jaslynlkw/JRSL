package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Profile extends Fragment {

    public Profile(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_profile, container, false);

        view.findViewById(R.id.btnSignOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear saved items
                DatabaseHandler db = new DatabaseHandler(getActivity());
                db.updateSavedItems("", "clear");
                Intent i3 = new Intent(getActivity(), Login.class);
                startActivity(i3);
            }
        });

        view.findViewById(R.id.OrdersOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.OrdersOption:
                        Intent i1 = new Intent(getActivity(), OrderHistory.class);
                        startActivity(i1);
                        break;
                    // Handle other views click
                }
            }
        });
        view.findViewById(R.id.profileCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.profileCart:
                        Intent i2 = new Intent(getActivity(), Cart.class);
                        startActivity(i2);
                        break;
                    // Handle other views click
                }
            }
        });
        return view;
    }
}

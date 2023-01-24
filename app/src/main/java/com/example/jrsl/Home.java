package com.example.jrsl;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home extends Fragment {

    // Urls of our images.
    int pic1 = R.drawable.karissa_blackstreetwearset;
    int pic2 = R.drawable.darcy_flaredjumpsuit;
    int pic3 = R.drawable.rena_whitedress;

    public Home() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home, container, false);

        // we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = view.findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(pic1, "KARISSA’S STYLE", "Dress up like @KarrisaLink with our All Black Collection"));
        sliderDataArrayList.add(new SliderData(pic2, "CLEO’S STYLE", "Dress up like @CleoTime with our Summertime Collection"));
        sliderDataArrayList.add(new SliderData(pic3, "DARCY’S STYLE", "Dress up like @DarcyDen with our Autumn Fest Collection"));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(getContext(), sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(5);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

        view.findViewById(R.id.imageViewCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Cart.class);
                startActivity(i);
            }
        });
        return view;
    }
}
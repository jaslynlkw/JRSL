package com.example.jrsl;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;


import android.os.Bundle;

public class Home extends AppCompatActivity {
        // Urls of our images.
        int pic1 = R.drawable.outfit1;
        int pic2 = R.drawable.outfit2;
        int pic3 = R.drawable.outfit3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            // we are creating array list for storing our image urls.
            ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

            // initializing the slider view.
            SliderView sliderView = findViewById(R.id.slider);

            // adding the urls inside array list
            sliderDataArrayList.add(new SliderData(pic1));
            sliderDataArrayList.add(new SliderData(pic2));
            sliderDataArrayList.add(new SliderData(pic3));

            // passing this array list inside our adapter class.
            SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

            // below method is used to set auto cycle direction in left to
            // right direction you can change according to requirement.
            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

            // below method is used to
            // setadapter to sliderview.
            sliderView.setSliderAdapter(adapter);

            // below method is use to set
            // scroll time in seconds.
            sliderView.setScrollTimeInSec(3);

            // to set it scrollable automatically
            // we use below method.
            sliderView.setAutoCycle(true);

            // to start autocycle below method is used.
            sliderView.startAutoCycle();
        }
    }
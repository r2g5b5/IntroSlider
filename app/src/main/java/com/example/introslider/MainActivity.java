package com.example.introslider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SliderPrefManager sliderPrefManager=new SliderPrefManager(this);
        if (sliderPrefManager.startSlider()){
            startActivity(new Intent(MainActivity.this,IntroSliderActivity.class));
            finish();
        }




    }
}
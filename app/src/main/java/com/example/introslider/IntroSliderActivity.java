package com.example.introslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroSliderActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnNext, btnSkip;
    private LinearLayout linearLayout;

    private SliderPrefManager prefManager;

    private final int[] layoutIDs = {
            R.layout.intro_slide1, R.layout.intro_slide2, R.layout.intro_slide3, R.layout.intro_slide4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        prefManager=new SliderPrefManager(this);


        viewPager = findViewById(R.id.indtro_viewpager);
        btnNext = findViewById(R.id.intro_btnNext);
        btnSkip = findViewById(R.id.intro_btnSkip);
        linearLayout = findViewById(R.id.intro_linearlayout_dots);

        SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter();

        viewPager.setAdapter(sliderPagerAdapter);

        showDots(0);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            if (viewPager.getCurrentItem()==viewPager.getAdapter().getCount()-1){
                startActivity(new Intent(IntroSliderActivity.this,MainActivity.class));
                prefManager.setStartSlider(false);
                finish();
            }else{
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }




            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(IntroSliderActivity.this,MainActivity.class));
                prefManager.setStartSlider(false);
                finish();



            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                showDots(position);

                if (position == viewPager.getAdapter().getCount() - 1) {
                    btnSkip.setVisibility(View.GONE);
                    btnNext.setText("Got it!");
                } else {
                    btnSkip.setVisibility(View.VISIBLE);
                    btnNext.setText("Next");
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    private void showDots(int pageNumber) {


        TextView[] txtDots = new TextView[layoutIDs.length];
        linearLayout.removeAllViews();
        for (int i = 0; i < layoutIDs.length; i++) {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226"));
            txtDots[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            if (pageNumber == i) {
                txtDots[i].setTextColor(Color.WHITE);
            } else {
                txtDots[i].setTextColor(Color.BLACK);
            }
            linearLayout.addView(txtDots[i]);
        }


    }

    class SliderPagerAdapter extends PagerAdapter {


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(IntroSliderActivity.this).inflate(layoutIDs[position]
                    , container, false);

            container.addView(view);
            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public int getCount() {
            return layoutIDs.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }


}
package com.example.introslider;

import android.content.Context;
import android.content.SharedPreferences;

public class SliderPrefManager {
    Context context;
    private SharedPreferences sharedPreferences;


    private static final String PREF_NAME="slider_pref";
    private static final String KEY_START="startSlider";
    public SliderPrefManager(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);

    }

    public boolean startSlider(){
        return sharedPreferences.getBoolean(KEY_START,true);
    }

    public void setStartSlider(boolean s){

        sharedPreferences.edit().putBoolean(KEY_START,s).apply();

    }


}

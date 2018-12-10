package com.example.gl.demo_android.mvvm.utils;

import android.databinding.BindingConversion;

import com.example.gl.demo_android.mvvm.SwordsMan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getName(SwordsMan swordsMan) {
        return swordsMan.getName();
    }

    @BindingConversion
    public static String convertData(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}

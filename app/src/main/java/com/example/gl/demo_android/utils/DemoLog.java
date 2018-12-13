package com.example.gl.demo_android.utils;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

public class DemoLog implements HttpLoggingInterceptor.Logger {

    private DemoLog(){
    }

    public static DemoLog getInstence() {
        return DemoLogHolder.sInstence;
    }

    public static void daggerLog(String log) {
        Log.d("gaol dagger", log);
    }

    public static void mvpLog(String log) {
        Log.d("gaol mvp", log);
    }

    public static void okhttpLog(String s) {
        Log.d("gaol okthhp", s);
    }
    public static void AsyncListUtilLog(String s) {
        Log.d("gaol AsyncListUtilLog", s);
    }


    public static void mvvmLog(String s) {
        Log.d("gaol mvvm", s);
    }

    public static void pagingLog(String log) {
        Log.d("gaol paging", log);
    }

    @Override
    public void log(String message) {
        Log.d("gaol okhttp", message);
    }

    private static class DemoLogHolder {
        private static final DemoLog sInstence = new DemoLog();
    }
}

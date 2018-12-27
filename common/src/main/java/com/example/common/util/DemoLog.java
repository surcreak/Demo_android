package com.example.common.util;

import android.util.Log;

public class DemoLog  {

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

    public static void viewlog_keyguard(String log) {
        Log.d("gaol viewlog_keyguard", log);
    }

    public static void viewlog_decoration(String log) {
        Log.d("gaol viewlog_decoration", log);
    }

    private static class DemoLogHolder {
        private static final DemoLog sInstence = new DemoLog();
    }
}

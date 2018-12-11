package com.example.gl.demo_android.asynclistutil;

public interface ItemSource {
    int getCount();
    Item getItem(int positiion);
    void close();
}

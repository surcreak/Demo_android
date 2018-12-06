package com.example.gl.demo_android.dagger;

import com.example.gl.demo_android.utils.Loger;

import javax.inject.Inject;

public class Watch {
    @Inject
    public Watch() {
    }

    public void work() {
        Loger.d("watch work");
    }
}

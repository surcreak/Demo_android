package com.example.gl.demo_android.dagger;

import com.example.gl.demo_android.utils.DemoLog;

import javax.inject.Inject;

public class Watch {
    @Inject
    public Watch() {
    }

    public void work() {
        DemoLog.daggerLog("watch work");
    }
}

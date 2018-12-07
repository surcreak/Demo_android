package com.example.gl.demo_android.dagger;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gl.demo_android.DemoApplication;
import com.example.gl.demo_android.R;
import com.example.gl.demo_android.utils.DemoLog;
import com.google.gson.Gson;

import javax.inject.Inject;

public class DaggerActivity2 extends AppCompatActivity {

    @Inject
    Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        setTitle(R.string.dagger2);

        DaggerDaggerActivity2Component.builder()
                .appComponent(DemoApplication.getInstance().getAppComponent())
                .build()
                .inject(this);

        DemoLog.daggerLog("DaggerActivity2 --> gaon.hashCode()="+gson.hashCode());
    }
}

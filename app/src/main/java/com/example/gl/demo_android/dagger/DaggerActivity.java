package com.example.gl.demo_android.dagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gl.demo_android.R;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    Watch watch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        setTitle(R.string.dagger);
        DaggerDaggerActivityComponent.create().inject(this);
        watch.work();
    }
}

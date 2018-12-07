package com.example.gl.demo_android.mvp.net;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetTaskModule.class)
public interface NetTaskComponent {
    NetTask getNetTask();
}

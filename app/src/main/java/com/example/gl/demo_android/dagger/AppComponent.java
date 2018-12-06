package com.example.gl.demo_android.dagger;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = GsonModule2.class)
public interface AppComponent {
    Gson gson();
}

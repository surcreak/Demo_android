package com.example.gl.demo_android.dagger;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule2 {
    //@ApplicationScope使用@Scope来自定义的注解，来实现Gson的全局单例
    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}

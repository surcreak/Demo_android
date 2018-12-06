package com.example.gl.demo_android.dagger;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//@Module：告诉Component，可以从这个类中获取依赖对象
@Module
public class GsonModule {
    //@Provides：通过方法来获取依赖对象的实例
    //@Singleton:实现局部单例，不是全局的单例。
    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}

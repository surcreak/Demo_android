package com.example.gl.demo_android.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class EngineModule3 {

    @Provides
    @Gasoline
    public Engine provideGasoline() {
        return new GasolineEngine();
    }

    @Provides
    @Diesel
    public Engine provideDiesel() {
        return new DieselEngine();
    }
}

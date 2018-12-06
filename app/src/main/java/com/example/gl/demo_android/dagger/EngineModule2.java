package com.example.gl.demo_android.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class EngineModule2 {

    @Provides
    @Named("Gasoline")
    public Engine prvideGasoline() {
        return new GasolineEngine();
    }

    @Provides
    @Named("Diesel")
    public Engine prvideDiesel() {
        return new DieselEngine();
    }
}

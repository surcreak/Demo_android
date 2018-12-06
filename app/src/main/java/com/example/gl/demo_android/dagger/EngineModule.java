package com.example.gl.demo_android.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class EngineModule {
    @Provides
    public Engine provideEngine() {
        return new GasolineEngine();
    }
}

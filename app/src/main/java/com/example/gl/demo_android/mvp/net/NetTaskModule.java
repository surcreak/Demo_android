package com.example.gl.demo_android.mvp.net;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetTaskModule {

    @Singleton
    @Provides
    NetTask provideIpInfoTask() {
        return new IpInfoTask();
    }
}

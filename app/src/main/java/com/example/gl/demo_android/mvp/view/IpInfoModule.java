package com.example.gl.demo_android.mvp.view;

import com.example.gl.demo_android.mvp.contract.IpInfoContract;

import dagger.Module;
import dagger.Provides;

@Module
public class IpInfoModule {
    private IpInfoContract.View mView;

    public IpInfoModule(IpInfoContract.View mView) {
        this.mView = mView;
    }

    @Provides
    IpInfoContract.View provideIpInfoContract() {
        return mView;
    }
}

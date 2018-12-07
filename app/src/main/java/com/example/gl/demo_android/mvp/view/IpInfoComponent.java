package com.example.gl.demo_android.mvp.view;

import com.example.gl.demo_android.mvp.annotation.FragmentScoped;
import com.example.gl.demo_android.mvp.net.NetTaskComponent;

import dagger.Component;

@FragmentScoped
@Component(dependencies = NetTaskComponent.class, modules = IpInfoModule.class)
public interface IpInfoComponent {
    void inject(IpInfoActivity ipInfoActivity);
}

package com.example.gl.demo_android;

import android.app.Application;

import com.example.gl.demo_android.dagger.AppComponent;
import com.example.gl.demo_android.dagger.DaggerAppComponent;
import com.example.gl.demo_android.utils.Loger;

public class DemoApplication extends Application {

    private static DemoApplication application;

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appComponent = DaggerAppComponent.builder().build();
        Loger.d("appComponent="+appComponent);
    }

    public static DemoApplication getInstance() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

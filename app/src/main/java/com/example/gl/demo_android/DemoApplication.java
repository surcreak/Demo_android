package com.example.gl.demo_android;

import android.app.Application;

import com.example.gl.demo_android.dagger.AppComponent;
import com.example.gl.demo_android.dagger.DaggerAppComponent;
import com.example.gl.demo_android.utils.DemoLog;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

public class DemoApplication extends Application {

    private static DemoApplication application;

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        //okhttpfinal初始化
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());

        //dagger2全局单例
        appComponent = DaggerAppComponent.builder().build();
        DemoLog.daggerLog("appComponent="+appComponent);
    }

    public static DemoApplication getInstance() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

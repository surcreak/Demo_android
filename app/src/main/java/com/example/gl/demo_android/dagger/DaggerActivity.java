package com.example.gl.demo_android.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gl.demo_android.DemoApplication;
import com.example.gl.demo_android.R;
import com.example.gl.demo_android.utils.Loger;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;

public class DaggerActivity extends AppCompatActivity {

    //简单用法
    @Inject
    Watch watch;

    //使用@Module获取，不能直接获取
    @Inject
    Gson gson;

    @Inject
    Gson gson1;

    // 1.直接注入abstract的类时报错，错误:Engine cannot be provided without an @Provides-annotated method.
    // 需要写个Module，这里是EngineModule
    // 2.如果有多个Engine，使用@Named来指定那个Engine，使用的EngineModule2.
    // 也可使用@Qualifier来指定使用那个Engine，使用EngineModule3.
    @Inject
    Car car;

    //懒加载
    @Inject
    Lazy<Watch> watchLazy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        setTitle(R.string.dagger);
        ButterKnife.bind(this);

        //通过Application里实例化APPComponent来实现全局单例。
        DaggerDaggerActivityComponent.builder()
                .appComponent(DemoApplication.getInstance().getAppComponent())
                .build()
                .inject(this);
        watch.work();

        String jsonData = "{'name':'zhangwuji', 'age':20}";
        Man man = gson.fromJson(jsonData, Man.class);
        Loger.d("man="+man);

        Loger.d("car run "+car.run());

        Loger.d("gson.hashCode()="+gson.hashCode());
        Loger.d("gson1.hashCode()="+gson1.hashCode());

        watchLazy.get().work();
    }

    @OnClick(R.id.dagger2)
    public void startDaggerActivity2() {
        startActivity(new Intent(this, DaggerActivity2.class));
    }

}

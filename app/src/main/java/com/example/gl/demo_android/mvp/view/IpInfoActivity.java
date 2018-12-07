package com.example.gl.demo_android.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gl.demo_android.DemoApplication;
import com.example.gl.demo_android.R;
import com.example.gl.demo_android.mvp.presenter.IpInfoPresenter;
import com.example.gl.demo_android.mvp.util.ActivityUtils;

import javax.inject.Inject;

public class IpInfoActivity extends AppCompatActivity {

    @Inject
    IpInfoPresenter ipInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipinfo);
        IpInfoFragment ipInfoFragment = (IpInfoFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (ipInfoFragment == null) {
            ipInfoFragment = IpInfoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    ipInfoFragment, R.id.contentFrame);
        }
        //IpInfoTask ipInfoTask = IpInfoTask.getInstance();
        //ipInfoPresenter = new IpInfoPresenter(ipInfoTask, ipInfoFragment);
        //ipInfoFragment.setPresenter(ipInfoPresenter);

        DaggerIpInfoComponent
                .builder()
                .ipInfoModule(new IpInfoModule(ipInfoFragment))
                .netTaskComponent(DemoApplication.getInstance().getNetTaskComponent())
                .build()
                .inject(this);
    }
}
package com.example.gl.demo_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.gl.demo_android.asynclistutil.AsyncListUtilActivity;
import com.example.gl.demo_android.dagger.DaggerActivity;
import com.example.gl.demo_android.mvp.view.IpInfoActivity;
import com.example.gl.demo_android.mvvm.MvvmDemoActivity;
import com.example.gl.demo_android.paging.PagingActivity;
import com.example.gl.demo_android.utils.DemoLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.dagger)
    Button btDagger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.dagger)
    public void startDaggerActivity() {
        DemoLog.daggerLog("startDaggerActivity");
        startActivity(new Intent(this, DaggerActivity.class));
    }

    @OnClick(R.id.mvp)
    public void startMvpActivity() {
        startActivity(new Intent(this, IpInfoActivity.class));
    }

    @OnClick(R.id.mvvm)
    public void startMvvmActivity() {
        startActivity(new Intent(this, MvvmDemoActivity.class));
    }

    @OnClick(R.id.asynclistutil)
    public void startAsyncListUtilActivity() {
        startActivity(new Intent(this, AsyncListUtilActivity.class));
    }

    @OnClick(R.id.paging)
    public void startPagingActivity() {
        startActivity(new Intent(this, PagingActivity.class));
    }
}

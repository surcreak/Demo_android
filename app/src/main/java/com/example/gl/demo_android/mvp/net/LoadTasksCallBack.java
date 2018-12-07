package com.example.gl.demo_android.mvp.net;

public interface LoadTasksCallBack<T> {
    void onSuccess(T t);
    void onStart();
    void onFailed();
    void onFinish();
}

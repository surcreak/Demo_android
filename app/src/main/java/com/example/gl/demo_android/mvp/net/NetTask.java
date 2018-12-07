package com.example.gl.demo_android.mvp.net;

public interface NetTask<T> {
    void execute(T data, LoadTasksCallBack callBack);
}

package com.example.gl.demo_android.mvp.net;

import io.reactivex.disposables.Disposable;

public interface NetTask<T> {
    Disposable execute(T data, LoadTasksCallBack callBack);
}

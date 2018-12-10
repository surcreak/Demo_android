package com.example.gl.demo_android.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {

    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(DemoLog.getInstence());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //java.lang.IllegalStateException: closed
        //由于response.body().string()调用了多次导致的，string()仅可调用一次。
        //在实际开发中，响应主体 RessponseBody 持有的资源可能会很大，
        // 所以 OkHttp 并不会将其直接保存到内存中，只是持有数据流连接。
        // 只有当我们需要时，才会从服务器获取数据并返回。
        // 同时，考虑到应用重复读取数据的可能性很小，
        // 所以将其设计为一次性流(one-shot)，读取后即 '关闭并释放资源'。

        //builder.addNetworkInterceptor(new LogInterceptor());
        builder.addNetworkInterceptor(loggingInterceptor);
        builder.callTimeout(3, TimeUnit.MINUTES);
        builder.readTimeout(3, TimeUnit.MINUTES);
        return builder.build();
    }
}

package com.example.gl.demo_android.okhttp;

import android.os.Build;

import com.example.gl.demo_android.utils.DemoLog;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        DemoLog.okhttpLog("request = "+chain.request().toString());
        DemoLog.okhttpLog("request getparam="+getParam(chain.request().body()));
        Request request = chain.request();
        Request.Builder requestBuilder = chain.request().newBuilder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //修改"User-Agent"欺骗服务器
//            requestBuilder.removeHeader("User-Agent");
//            requestBuilder.addHeader("User-Agent",WebSettings.getDefaultUserAgent(DemoApplication.getInstance()));
//            requestBuilder.addHeader("User-Agent",
//                    "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
        }

        Response response = chain.proceed(requestBuilder.build());
        DemoLog.okhttpLog("response = "+response.toString());
        DemoLog.okhttpLog("response.body() = "+response.body().string());
        return response;
    }

    private String getParam(RequestBody requestBody) {
        Buffer buffer = new Buffer();
        String logparm;
        try {
            requestBody.writeTo(buffer);
            logparm = buffer.readUtf8();
            logparm = URLDecoder.decode(logparm, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return logparm;
    }
}

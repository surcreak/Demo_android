package com.example.gl.demo_android.mvp.net;

import com.example.gl.demo_android.mvp.model.IpInfo;
import com.example.gl.demo_android.utils.DemoLog;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class IpInfoTask implements NetTask<String> {

    private static final String HOST = "http://ip.taobao.com/service/getIpInfo.php";

    private IpInfoTask() {
    }

    public static IpInfoTask getInstance() {
        return IpInfoTaskHolder.sInstance;
    }

    private static class IpInfoTaskHolder {
        private static final IpInfoTask sInstance = new IpInfoTask();
    }

    @Override
    public void execute(String ip, final LoadTasksCallBack callBack) {
        RequestParams requestParams = new RequestParams();
        requestParams.addFormDataPart("ip", ip);
        HttpRequest.post(HOST, requestParams, new BaseHttpRequestCallback<IpInfo>(){
            @Override
            public void onStart() {
                super.onStart();
                callBack.onStart();
            }

            @Override
            protected void onSuccess(IpInfo ipInfo) {
                super.onSuccess(ipInfo);
                callBack.onSuccess(ipInfo);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                callBack.onFinish();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DemoLog.mvpLog("omFailure msg="+msg);
                callBack.onFailed();
            }
        });
    }
}

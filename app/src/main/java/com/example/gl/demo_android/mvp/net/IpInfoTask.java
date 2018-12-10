package com.example.gl.demo_android.mvp.net;

import com.example.gl.demo_android.mvp.model.IpInfo;
import com.example.gl.demo_android.utils.DemoLog;
import com.example.gl.demo_android.utils.OkHttpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class IpInfoTask implements NetTask<String> {

    private static final String HOST = "http://ip.taobao.com/service/";

    public IpInfoTask() {
        createRetrofit();
    }

    public static IpInfoTask getInstance() {
        return IpInfoTaskHolder.sInstance;
    }

    private static class IpInfoTaskHolder {
        private static final IpInfoTask sInstance = new IpInfoTask();
    }

    private Retrofit retrofit;

    private void createRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpUtil.getOkHttpClient())
                .build();
    }

    @Override
    public Disposable execute(String ip, final LoadTasksCallBack callBack) {
        IpService ipService = retrofit.create(IpService.class);
        Disposable disposable = ipService.getIpInfo(ip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceObserver<IpInfo>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        callBack.onStart();
                    }

                    @Override
                    public void onNext(IpInfo ipInfo) {
                        callBack.onSuccess(ipInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        DemoLog.mvpLog("OnError e="+e.toString());
                        e.printStackTrace();
                        callBack.onFailed();
                    }

                    @Override
                    public void onComplete() {
                        callBack.onFinish();
                    }
                });
        return disposable;

        //simple MVP
//        RequestParams requestParams = new RequestParams();
//        requestParams.addFormDataPart("ip", ip);
//        HttpRequest.post(HOST, requestParams, new BaseHttpRequestCallback<IpInfo>(){
//            @Override
//            public void onStart() {
//                super.onStart();
//                callBack.onStart();
//            }
//
//            @Override
//            protected void onSuccess(IpInfo ipInfo) {
//                super.onSuccess(ipInfo);
//                callBack.onSuccess(ipInfo);
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                callBack.onFinish();
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//                DemoLog.mvpLog("omFailure msg="+msg);
//                callBack.onFailed();
//            }
//        });
//        return null;
    }
}

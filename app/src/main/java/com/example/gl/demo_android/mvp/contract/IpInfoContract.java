package com.example.gl.demo_android.mvp.contract;

import com.example.gl.demo_android.mvp.base.BaseView;
import com.example.gl.demo_android.mvp.model.IpInfo;

public interface IpInfoContract {
    interface Presenter{
        void getIpInfo(String ip);
    }

    interface View extends BaseView<Presenter> {
        void setIpInfo(IpInfo ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        boolean isActive();
    }
}

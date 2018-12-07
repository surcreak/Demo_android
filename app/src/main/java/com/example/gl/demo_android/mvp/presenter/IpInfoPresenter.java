package com.example.gl.demo_android.mvp.presenter;

import com.example.gl.demo_android.mvp.contract.IpInfoContract;
import com.example.gl.demo_android.mvp.model.IpInfo;
import com.example.gl.demo_android.mvp.net.LoadTasksCallBack;
import com.example.gl.demo_android.mvp.net.NetTask;

public class IpInfoPresenter implements IpInfoContract.Presenter, LoadTasksCallBack<IpInfo> {
    private NetTask netTask;
    private IpInfoContract.View addTaskView;

    public IpInfoPresenter(NetTask netTask, IpInfoContract.View addTaskView) {
        this.netTask = netTask;
        this.addTaskView = addTaskView;
    }

    @Override
    public void getIpInfo(String ip) {
        netTask.execute(ip, this);
    }

    @Override
    public void onSuccess(IpInfo ipInfo) {
        if (addTaskView.isActive()) {
            addTaskView.setIpInfo(ipInfo);
        }
    }

    @Override
    public void onStart() {
        if (addTaskView.isActive()) {
            addTaskView.showLoading();
        }
    }

    @Override
    public void onFailed() {
        if (addTaskView.isActive()) {
            addTaskView.showError();
            addTaskView.hideLoading();
        }
    }

    @Override
    public void onFinish() {
        if (addTaskView.isActive()) {
            addTaskView.hideLoading();
        }
    }
}

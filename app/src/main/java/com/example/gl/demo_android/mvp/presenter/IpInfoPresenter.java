package com.example.gl.demo_android.mvp.presenter;

import com.example.gl.demo_android.mvp.contract.IpInfoContract;
import com.example.gl.demo_android.mvp.model.IpInfo;
import com.example.gl.demo_android.mvp.net.LoadTasksCallBack;
import com.example.gl.demo_android.mvp.net.NetTask;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class IpInfoPresenter implements IpInfoContract.Presenter, LoadTasksCallBack<IpInfo> {
    private NetTask netTask;
    private IpInfoContract.View addTaskView;
    private CompositeDisposable mDisposables;
    private Disposable disposable;

    @Inject
    public IpInfoPresenter(NetTask netTask, IpInfoContract.View addTaskView) {
        this.netTask = netTask;
        this.addTaskView = addTaskView;
        mDisposables = new CompositeDisposable();
    }

    @Inject
    void setPresenter() {
        addTaskView.setPresenter(this);
    }

    @Override
    public void getIpInfo(String ip) {
        disposable = netTask.execute(ip, this);
        subscribe();
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

    @Override
    public void subscribe() {
        if (disposable != null) {
            mDisposables.add(disposable);
        }
    }

    @Override
    public void unsubscribe() {
        if (mDisposables != null) {
            mDisposables.dispose();
        }
    }
}

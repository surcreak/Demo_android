package com.example.gl.demo_android.asynclistutil;

import android.support.annotation.NonNull;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;

//实现 ScrollListener 来调用 AsyncListUtil 的 onRangeChanged() 方法
public class ScrollListener extends RecyclerView.OnScrollListener {

    AsyncListUtil<Item> listUtil;

    public ScrollListener(AsyncListUtil listUtil) {
        this.listUtil = listUtil;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        //DemoLog.AsyncListUtilLog("onScrollStateChanged");
        listUtil.onRangeChanged();
    }
}

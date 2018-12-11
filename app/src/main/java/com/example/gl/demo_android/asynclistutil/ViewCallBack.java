package com.example.gl.demo_android.asynclistutil;

import android.support.annotation.NonNull;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gl.demo_android.utils.DemoLog;

// AsyncListUtil 通过 ViewCallback 主要是做两件事：
// 1.通知视图数据已经更新（onDataRefresh）；
// 2.了解当前视图所展示数据的位置，从而确定什么时候获取更多数据或释放掉
// 目前不在窗口内的旧数据（getItemRangeInto）。
public class ViewCallBack extends AsyncListUtil.ViewCallback {

    private RecyclerView recyclerView;

    public ViewCallBack(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void getItemRangeInto(@NonNull int[] outRange) {
        DemoLog.AsyncListUtilLog("getItemRangeInto");
//        if (outRange != null) {
//            DemoLog.AsyncListUtilLog("outRange is null");
//            return;
//        }

        getOutRange(outRange);

//        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//            outRange[0] = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//            outRange[1] = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//        }
        DemoLog.AsyncListUtilLog("outRange[0]="+outRange[0]);
        DemoLog.AsyncListUtilLog("outRange[1]="+outRange[1]);

        if (outRange[0] == -1 && outRange[1] == -1) {
            outRange[0] = 0;
            outRange[1] = 0;
        }
    }

    private void getOutRange(int[] outRange){
        if (outRange == null) {
            outRange = new int [2];
        }
        outRange[0] = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        outRange[1] = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
    }

    @Override
    public void onDataRefresh() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemLoaded(int position) {
        recyclerView.getAdapter().notifyItemChanged(position);
    }
}

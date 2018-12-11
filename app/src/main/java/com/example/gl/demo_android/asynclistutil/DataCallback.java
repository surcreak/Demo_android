package com.example.gl.demo_android.asynclistutil;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.util.AsyncListUtil;

import com.example.gl.demo_android.utils.DemoLog;

//DataCallback 是用来为 AsyncListUtil 提供数据访问，其中所有方法都会在后台线程中调用。
public class DataCallback extends AsyncListUtil.DataCallback<Item> {

    ItemSource itemSource;

    public DataCallback(ItemSource itemSource) {
        this.itemSource = itemSource;
    }

    //返回刷新后的数据个数。
    @Override
    public int refreshData() {
        return itemSource.getCount();
    }

    //当 AsyncListUtil 需要更多数据时，将会在后台线程调用该方法。
    // fillData只调用了一次,是由于ViewCallBack的getItemRangeInto中outRange为空了。
    @Override
    public void fillData(@NonNull Item[] data, int startPosition, int itemCount) {
        DemoLog.AsyncListUtilLog("startPosition = "+startPosition);
        DemoLog.AsyncListUtilLog("itemCount = "+itemCount);
        DemoLog.AsyncListUtilLog("data.length = "+ data.length);
        if (data != null) {
            for (int i = 0; i < itemCount; i++) {
                data[i] = itemSource.getItem(startPosition + i);
                //模拟耗时任务，故意休眠一定时延。
                SystemClock.sleep(100);
            }
        }
    }

    public void close() {
        itemSource.close();
    }
}

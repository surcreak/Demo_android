package com.example.gl.demo_android.asynclistutil;

import android.support.annotation.NonNull;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gl.demo_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncAdapter extends RecyclerView.Adapter<AsyncAdapter.AsyncViewHolder> {

    private ItemSource itemSource;
    private RecyclerView recyclerView;
    private DataCallback dataCallback;
    private ViewCallBack viewCallBack;
    private AsyncListUtil<Item> listUtil;
    private ScrollListener scrollListener;

    public AsyncAdapter(ItemSource itemSource, RecyclerView recyclerView) {
        this.itemSource = itemSource;
        this.recyclerView = recyclerView;
        dataCallback = new DataCallback(itemSource);
        viewCallBack = new ViewCallBack(recyclerView);
        listUtil = new AsyncListUtil(Item.class, 10,
                dataCallback, viewCallBack);
        scrollListener = new ScrollListener(listUtil);
    }

    public void onStart(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(scrollListener);
        listUtil.refresh();
    }

    public void onStop(RecyclerView recyclerView) {
        recyclerView.removeOnScrollListener(scrollListener);
        dataCallback.close();
    }

    @NonNull
    @Override
    public AsyncViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.async_item, viewGroup, false);
        return new AsyncViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsyncViewHolder viewHolder, int i) {
        Item item = listUtil.getItem(i);
//        DemoLog.AsyncListUtilLog("item = " + item);
//        DemoLog.AsyncListUtilLog("onBindViewHolder i = " + i);
        viewHolder.bindView(item, i);
    }

    @Override
    public int getItemCount() {
        return listUtil.getItemCount();
    }

    class AsyncViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView tvTitle;
        @BindView(R.id.content)
        public TextView tvContent;

        public AsyncViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(Item item, int position) {
            if (item != null) {
                tvTitle.setText(item.id+"  "+item.title);
                tvContent.setText(item.content);
            }
        }
    }
}

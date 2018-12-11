package com.example.gl.demo_android.asynclistutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gl.demo_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncListUtilActivity extends AppCompatActivity {

    @BindView(R.id.aynclistutil_recycle)
    RecyclerView recyclerView;

    private ItemSource itemSource;
    private AsyncAdapter asyncAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynclistutil);
        ButterKnife.bind(this);
        setTitle("AsyncListUtil");

        itemSource = new SQLiteItemSource(Database.getDatabase(this,
                "database.sqlite"));
        asyncAdapter = new AsyncAdapter(itemSource, recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(asyncAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        asyncAdapter.onStart(recyclerView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        asyncAdapter.onStop(recyclerView);
    }
}

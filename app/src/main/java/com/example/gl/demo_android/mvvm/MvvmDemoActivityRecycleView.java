package com.example.gl.demo_android.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.gl.demo_android.R;
import com.example.gl.demo_android.databinding.ActivityMvvmRecycleBinding;
import com.example.gl.demo_android.mvvm.adpter.SwordsmanAdapter;

import java.util.ArrayList;
import java.util.List;

public class MvvmDemoActivityRecycleView extends AppCompatActivity {

    private ActivityMvvmRecycleBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_recycle);
        setTitle("Mvvm recycle");
        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(MvvmDemoActivityRecycleView.this);
        binding.recycle.setLayoutManager(manager);
        SwordsmanAdapter adapter = new SwordsmanAdapter(getList());
        binding.recycle.setAdapter(adapter);
    }

    private List<SwordsMan> getList() {
        List<SwordsMan> list = new ArrayList<>();
        SwordsMan swordsMan1 = new SwordsMan("杨影枫", "SS");
        SwordsMan swordsMan2 = new SwordsMan("月眉儿", "A");
        list.add(swordsMan1);
        list.add(swordsMan2);
        return list;
    }
}

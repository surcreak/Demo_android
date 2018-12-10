package com.example.gl.demo_android.mvvm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gl.demo_android.R;
import com.example.gl.demo_android.databinding.ActivityMvvmBinding;
import com.example.gl.demo_android.utils.DemoLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MvvmDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mvvm);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_mvvm);
        setTitle(R.string.mvvm);
        SwordsMan swordsMan = new SwordsMan("张无忌", "s");
        binding.setSwordsman(swordsMan);
        binding.btLayout.setOnClickListener(l -> {
            DemoLog.mvvmLog("bt layout");
            startActivity(new Intent(this, MvvmDemoActivityUpdata.class));
        });
        binding.setName("风清扬");
        binding.setAge(30);
        binding.setMan(true);

        ArrayList list = new ArrayList();
        list.add("0");
        list.add("1");
        binding.setList(list);

        Map map = new HashMap();
        map.put("age", "30");
        binding.setMap(map);

        String[] arrays = {"张无忌", "慕容龙城"};
        binding.setArrays(arrays);

        binding.btRecycle.setOnClickListener(l -> {
            startActivity(new Intent(this, MvvmDemoActivityRecycleView.class));
        });
    }
}

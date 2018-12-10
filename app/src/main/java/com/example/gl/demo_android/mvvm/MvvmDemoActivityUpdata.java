package com.example.gl.demo_android.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gl.demo_android.R;
import com.example.gl.demo_android.databinding.MyActivityBinding;
import com.example.gl.demo_android.mvvm.model.ObFSwordsman;
import com.example.gl.demo_android.mvvm.model.ObSwordsman;
import com.example.gl.demo_android.utils.DemoLog;

import java.util.Date;

public class MvvmDemoActivityUpdata extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.mvvm2);
        MyActivityBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_mvvm_updata);
        SwordsMan swordsMan = new SwordsMan("独孤求败", "SS");
        binding.setSwordsMan(swordsMan);

        //使用@BindingConversion
        /**
         * @see com.example.gl.demo_android.mvvm.utils.Utils.convertData()
         */
        binding.setTime(new Date());

        ObSwordsman obSwordsman = new ObSwordsman("任我行", "A");
        binding.setObserableMan(obSwordsman);

        binding.btUpdataObswordsman.setOnClickListener(l -> {
            obSwordsman.setName("石破天");
        });

        ObFSwordsman obFSwordsman = new ObFSwordsman("风清扬", "S");
        binding.setObFSwordsman(obFSwordsman);
        binding.btObfSwordSmanUpdate.setOnClickListener(l -> {
            obFSwordsman.name.set("令狐冲");
        });

        ObservableArrayList<SwordsMan> swordsMans = new ObservableArrayList<>();
        SwordsMan swordsMan1 = new SwordsMan("张无忌", "S");
        SwordsMan swordsMan2 = new SwordsMan("周芷若", "B");
        swordsMans.add(swordsMan1);
        swordsMans.add(swordsMan2);
        binding.setList(swordsMans);
        binding.btListUpdata.setOnClickListener(l -> {
            DemoLog.mvvmLog("btListUpdata");
            swordsMan1.setName("杨过");
            swordsMan2.setName("小龙女");
            //???非要加下面这句话
            swordsMans.add(swordsMan1);
        });

        ObSwordsman editSwordsMan = new ObSwordsman("任我行", "A");
        binding.setEditSwordsMan(editSwordsMan);
        binding.btUpdataBind.setOnClickListener(l -> {
            editSwordsMan.setName("任我行");
        });
    }
}

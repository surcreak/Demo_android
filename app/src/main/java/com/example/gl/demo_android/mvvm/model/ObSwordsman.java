package com.example.gl.demo_android.mvvm.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.gl.demo_android.BR;

public class ObSwordsman extends BaseObservable {
    private String name;
    private String level;

    public ObSwordsman(String name, String level) {
        this.name = name;
        this.level = level;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }
}

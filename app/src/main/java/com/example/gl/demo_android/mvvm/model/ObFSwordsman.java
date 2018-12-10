package com.example.gl.demo_android.mvvm.model;

import android.databinding.ObservableField;

public class ObFSwordsman {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> lever = new ObservableField<>();

    public ObFSwordsman(String name, String lever) {
        this.name.set(name);
        this.lever.set(lever);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<String> getLever() {
        return lever;
    }

    public void setLever(ObservableField<String> lever) {
        this.lever = lever;
    }
}

package com.example.gl.demo_android.dagger;

import dagger.Component;

@Component
public interface DaggerActivityComponent {
    void inject(DaggerActivity activity);
}

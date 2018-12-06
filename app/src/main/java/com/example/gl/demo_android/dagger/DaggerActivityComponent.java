package com.example.gl.demo_android.dagger;

import dagger.Component;

//@Component:用来指定Module，可以指定多个
@ApplicationScope
@Component(modules = {EngineModule.class,
        EngineModule2.class, EngineModule3.class}, dependencies = AppComponent.class)
public interface DaggerActivityComponent {
    void inject(DaggerActivity activity);
}

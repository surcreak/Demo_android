package com.example.gl.demo_android.dagger;

import javax.inject.Inject;

public class Car {
    private Engine engine;

    // 1、使用@Named来指定那个EngineModule2中的private
//    @Inject
//    public Car(@Named("Diesel") Engine engine) {
//        this.engine = engine;
//    }

    //2、使用@Qualifier自定义注解，指定使用那个Engine，使用的是EngineModule3.
    @Inject
    public Car(@Gasoline Engine engine) {
        this.engine = engine;
    }

    public String run() {
        return engine.work();
    }
}

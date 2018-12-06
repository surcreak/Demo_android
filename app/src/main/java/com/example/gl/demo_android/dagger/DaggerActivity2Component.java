package com.example.gl.demo_android.dagger;

import dagger.Component;

// 通过dependencies来引入AppComponent
// 下面的“@ApplicationScope”要和AppComponent的注解不同，要不会报错
// Component和他所依赖的Component不能公用相同的Scope，每个Component都要有自己的Scope，编译时会报错，
// 因为这有可能破坏Scope的范围，可详见https://github.com/google/dagger/issues/107#issuecomment-71073298。
@ApplicationScope
@Component(dependencies = AppComponent.class)
public interface DaggerActivity2Component {
    void inject(DaggerActivity2 activity);
}

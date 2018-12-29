package com.example.islam.mitelapp.app;

import android.app.Application;

import com.example.islam.mitelapp.data.MitelRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        MitelModule.class,
        ActivityBuilder.class})
public interface MitelComponent {
    MitelRepository getMitelRepository();

    void inject(MitelAPP app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);
        MitelComponent build();
    }
}

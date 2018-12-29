package com.example.islam.mitelapp.app;

import android.app.Application;

import com.example.islam.mitelapp.data.MitelRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        MitelModule.class,
        ActivityBuilder.class})
public interface MitelComponent extends AndroidInjector<DaggerApplication> {

    void inject(MitelAPP app);
    MitelRepository getMitelRepository();

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        MitelComponent build();
    }
}
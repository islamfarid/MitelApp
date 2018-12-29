package com.example.islam.mitelapp.app;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public class MitelAPP extends DaggerApplication {
    RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);

    }
    public void mustDie(Object object) {
        if (mRefWatcher != null) {
            mRefWatcher.watch(object);
        }
    }
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        MitelComponent appComponent = DaggerMitelComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}

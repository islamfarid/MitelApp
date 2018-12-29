package com.example.islam.mitelapp.app;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public class MitelAPP extends Application implements HasActivityInjector {
//    MitelComponent mMitelComponent;

    RefWatcher mRefWatcher;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
        DaggerMitelComponent.builder()
                .application(this)
                .build().inject(this);

    }

    public void mustDie(Object object) {
        if (mRefWatcher != null) {
            mRefWatcher.watch(object);
        }
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}

package com.example.islam.mitelapp.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public class MitelAPP extends Application {
    MitelComponent mMitelComponent;
    RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
        mMitelComponent = DaggerMitelComponent.builder()
                .application(this)
                .build();

    }

    public MitelComponent getTheoPlayerIntegrationComponentComponent() {
        return mMitelComponent;
    }

    public void mustDie(Object object) {
        if (mRefWatcher != null) {
            mRefWatcher.watch(object);
        }
    }
}

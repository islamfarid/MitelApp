package com.example.islam.mitelapp.app;

import android.app.Activity;

import com.example.islam.mitelapp.wether.WeatherActivity;
import com.example.islam.mitelapp.wether.dagger.WeatherComponent;
import com.example.islam.mitelapp.wether.dagger.WeatherModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by mertsimsek on 25/05/2017.
 */
@Module
public abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ActivityKey(WeatherActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindWeatherActivity(WeatherComponent.Builder builder);
}

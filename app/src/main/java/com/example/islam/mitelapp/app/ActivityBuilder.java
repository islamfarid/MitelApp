package com.example.islam.mitelapp.app;

import com.example.islam.mitelapp.wether.WeatherActivity;
import com.example.islam.mitelapp.wether.dagger.WeatherModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mertsimsek on 25/05/2017.
 */
@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = WeatherModule.class)
     abstract WeatherActivity bindWeatherActivity();
}

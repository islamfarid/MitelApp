package com.example.islam.mitelapp.wether.dagger;



import com.example.islam.mitelapp.app.MitelComponent;
import com.example.islam.mitelapp.common.FragmentScoped;
import com.example.islam.mitelapp.wether.WeatherActivity;

import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by islam on 03/12/16.
 */

@FragmentScoped
@Subcomponent(modules = WeatherModule.class)
public interface WeatherComponent extends AndroidInjector<WeatherActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WeatherActivity>{}
}

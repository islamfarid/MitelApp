package com.example.islam.mitelapp.wether.dagger;



import com.example.islam.mitelapp.app.MitelComponent;
import com.example.islam.mitelapp.common.FragmentScoped;
import com.example.islam.mitelapp.wether.WeatherActivity;

import dagger.Component;

/**
 * Created by islam on 03/12/16.
 */

@FragmentScoped
@Component(dependencies = MitelComponent.class,
        modules = WeatherModule.class)
public interface WeatherComponent {
    void inject(WeatherActivity weatherActivity);
}

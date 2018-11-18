package com.example.islam.mitelapp.wether.dagger;


import com.example.islam.mitelapp.data.MitelRepository;
import com.example.islam.mitelapp.wether.WeatherBusiness;
import com.example.islam.mitelapp.wether.WeatherContract;
import com.example.islam.mitelapp.wether.WeatherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 03/12/16.
 */
@Module
public class WeatherModule {
    private final WeatherContract.View mView;

    public WeatherModule(WeatherContract.View view) {
        this.mView = view;
    }

    @Provides
    WeatherContract.View provideVideoListView() {
        return mView;
    }

    @Provides
    WeatherContract.Presenter provideVideoListPresenter(WeatherBusiness weatherBusiness) {
        return new WeatherPresenter(mView, weatherBusiness);
    }

    @Provides
    WeatherBusiness provideVideoListBusiness(MitelRepository mitelRepository) {
        return new WeatherBusiness(mitelRepository);
    }


}

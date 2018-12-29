package com.example.islam.mitelapp.wether.dagger;


import com.example.islam.mitelapp.R;
import com.example.islam.mitelapp.common.ActivityUtils;
import com.example.islam.mitelapp.data.MitelRepository;
import com.example.islam.mitelapp.wether.WeatherActivity;
import com.example.islam.mitelapp.wether.WeatherBusiness;
import com.example.islam.mitelapp.wether.WeatherContract;
import com.example.islam.mitelapp.wether.WeatherFragment;
import com.example.islam.mitelapp.wether.WeatherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 03/12/16.
 */
@Module
public class WeatherModule {

    @Provides
    WeatherContract.View provideMainView(WeatherActivity weatherActivity) {
        WeatherFragment weatherFragment =
                (WeatherFragment) weatherActivity.getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (weatherFragment == null) {
            // Create the fragment
            weatherFragment = WeatherFragment.newInstance();
            weatherFragment.setArguments(weatherActivity.getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(
                    weatherActivity.getSupportFragmentManager(), weatherFragment, R.id.contentFrame);
        }
        return weatherFragment;
    }


    @Provides
    WeatherContract.Presenter provideVideoListPresenter(WeatherBusiness weatherBusiness, WeatherContract.View mView) {
        return new WeatherPresenter(mView, weatherBusiness);
    }

    @Provides
    WeatherBusiness provideVideoListBusiness(MitelRepository mitelRepository) {
        return new WeatherBusiness(mitelRepository);
    }


}

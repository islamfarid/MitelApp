package com.example.islam.mitelapp.wether;

import android.os.Bundle;

import com.example.islam.mitelapp.R;
import com.example.islam.mitelapp.app.BaseActivity;
import com.example.islam.mitelapp.app.MitelAPP;
import com.example.islam.mitelapp.common.ActivityUtils;
import com.example.islam.mitelapp.wether.dagger.DaggerWeatherComponent;
import com.example.islam.mitelapp.wether.dagger.WeatherModule;

import javax.inject.Inject;

public class WeatherActivity extends BaseActivity {
    @Inject
    WeatherContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        WeatherFragment weatherFragment =
                (WeatherFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (weatherFragment == null) {
            // Create the fragment
            weatherFragment = WeatherFragment.newInstance();
            weatherFragment.setArguments(getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), weatherFragment, R.id.contentFrame);
        }

        DaggerWeatherComponent.builder().
                mitelComponent(((MitelAPP) getApplication()).
                        getTheoPlayerIntegrationComponentComponent()).weatherModule(new WeatherModule(weatherFragment)).build().inject(this);
    }
}

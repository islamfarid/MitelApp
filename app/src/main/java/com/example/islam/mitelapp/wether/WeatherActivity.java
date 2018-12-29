package com.example.islam.mitelapp.wether;

import android.os.Bundle;

import com.example.islam.mitelapp.R;
import com.example.islam.mitelapp.app.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class WeatherActivity extends BaseActivity {
    @Inject
    WeatherContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_weather);
    }
}

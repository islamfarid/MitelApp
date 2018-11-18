package com.example.islam.mitelapp.wether;

import com.example.islam.mitelapp.data.models.CurrentWeatherModel;

import java.util.ArrayList;

public interface OnSelectedListChanged {
    void onItemsChanged(ArrayList<CurrentWeatherModel> currentWeatherModels);
}

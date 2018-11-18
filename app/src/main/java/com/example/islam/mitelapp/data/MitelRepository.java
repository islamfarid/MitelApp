package com.example.islam.mitelapp.data;

import com.example.islam.mitelapp.data.models.CurrentWeatherModel;
import com.example.islam.mitelapp.data.models.LocationModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public interface MitelRepository {
    Single<ArrayList<LocationModel>> searchLocationsByString(String apiKey, String keyword);
    Observable<CurrentWeatherModel> getWeatherForLatAndLong(String apiKey, String keyword,
                                                                   double latitude, double longitude);
    void setValue(String key, String value);
    String getStringValue(String key, String defaultValue);

}

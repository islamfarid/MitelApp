package com.example.islam.mitelapp.data.remote;


import com.example.islam.mitelapp.data.models.CurrentWeatherModel;
import com.example.islam.mitelapp.data.models.LocationModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MiletRemoteRepo {
    @GET("search.json")
    Single<ArrayList<LocationModel>> searchLocationsByString(@Query("key") String apiKey,
                                                             @Query("q") String keyword);

    @GET("current.json")
    Observable<CurrentWeatherModel> getWeatherForLatAndLong(@Query("key") String apiKey, @Query("q") String keyword,
                                                            @Query("lat") double latitude, @Query("long") double longitude);
}
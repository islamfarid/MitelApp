package com.example.islam.mitelapp.data;

import android.content.Context;

import com.example.islam.mitelapp.data.local.SharedPreference;
import com.example.islam.mitelapp.data.models.CurrentWeatherModel;
import com.example.islam.mitelapp.data.models.LocationModel;
import com.example.islam.mitelapp.data.remote.MiletRemoteRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public class MitelRepositoryImp implements MitelRepository {
    private MiletRemoteRepo mMiletRemoteRepo;
    private SharedPreference mSharedPreference;
    private Context context;
    @Inject
    public MitelRepositoryImp(Context context, MiletRemoteRepo assetsRepo , SharedPreference sharedPreference) {
        this.context = context;
        this.mMiletRemoteRepo = assetsRepo;
        this.mSharedPreference = sharedPreference;
    }
    @Override
    public Single<ArrayList<LocationModel>> searchLocationsByString(String apiKey, String keyword) {
        return mMiletRemoteRepo.searchLocationsByString(apiKey,keyword);
    }

    @Override
    public Observable<CurrentWeatherModel> getWeatherForLatAndLong(String apiKey, String keyword, double latitude, double longitude) {
        return mMiletRemoteRepo.getWeatherForLatAndLong(apiKey,keyword,latitude,longitude);
    }

    @Override
    public void setValue(String key, String value) {
        mSharedPreference.setValue(key,value);
    }

    @Override
    public String getStringValue(String key, String defaultValue) {
        return mSharedPreference.getStringValue(key,defaultValue);
    }

}

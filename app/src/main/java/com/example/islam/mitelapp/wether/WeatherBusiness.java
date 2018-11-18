package com.example.islam.mitelapp.wether;

import com.example.islam.mitelapp.common.Constants;
import com.example.islam.mitelapp.data.MitelRepository;
import com.example.islam.mitelapp.data.models.CurrentWeatherModel;
import com.example.islam.mitelapp.data.models.LocationModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WeatherBusiness {
    private MitelRepository mMitelRepository;
    private ArrayList<CurrentWeatherModel> mCurrentSelectedWeathLists;
    private WeatherScreenScenario mWeatherScreenScenario;

    @Inject
    public WeatherBusiness(MitelRepository theoPlayerIntegrationRepository) {
        mMitelRepository = theoPlayerIntegrationRepository;
    }

    Single<ArrayList<LocationModel>> getLocationsList(String keyword) {
        mWeatherScreenScenario = WeatherScreenScenario.SEARCH_SCENARIO;
        return mMitelRepository.searchLocationsByString(Constants.API_KEY, keyword);
    }

    Observable<ArrayList<CurrentWeatherModel>> getWeatherAsPerLocations(LocationModel locationModel, String keyword) {
        mWeatherScreenScenario = WeatherScreenScenario.LIST_OF_LOCATIONS_SCENARIO;
        return mMitelRepository.getWeatherForLatAndLong(Constants.API_KEY, keyword, locationModel.getLat(), locationModel.getLon()).map(currentWeatherModel -> {
            if (mCurrentSelectedWeathLists == null) {
                mCurrentSelectedWeathLists = new ArrayList<>();
                mCurrentSelectedWeathLists.add(currentWeatherModel);
            } else {
                mCurrentSelectedWeathLists.add(currentWeatherModel);
            }
            currentWeatherModel.getLocation().setmKEywordSearch(keyword);
            mMitelRepository.setValue(Constants.WEATHER_DATA, new Gson().toJson(mCurrentSelectedWeathLists));
            return mCurrentSelectedWeathLists;
        });
    }

    Observable<CurrentWeatherModel> getWeatherAsPerLocations(CurrentWeatherModel currentWeather) {
        mWeatherScreenScenario = WeatherScreenScenario.LIST_OF_LOCATIONS_SCENARIO;
        return mMitelRepository.getWeatherForLatAndLong(Constants.API_KEY, currentWeather.getLocation().getmKEywordSearch(), currentWeather
                .getLocation().getLat(), currentWeather.
                getLocation().getLon()).map(currentWeatherModel -> {

            currentWeatherModel.getLocation().setmKEywordSearch(currentWeather.getLocation().getmKEywordSearch());
            return currentWeatherModel;
        }).subscribeOn(Schedulers.io());
    }

    public Observable<ArrayList<CurrentWeatherModel>> onSwipeRefresh() {
        ArrayList<Observable<CurrentWeatherModel>> observables = new ArrayList<>();
        for (CurrentWeatherModel currentWeatherModel : mCurrentSelectedWeathLists) {
            observables.add(getWeatherAsPerLocations(currentWeatherModel));
        }
        return Observable.zip(observables, objects -> {
            if (objects != null && objects.length > 0) {
                ArrayList<CurrentWeatherModel> currentWeatherModels = new ArrayList<>();
                for (Object object : objects) {
                    currentWeatherModels.add((CurrentWeatherModel) object);
                }
                mCurrentSelectedWeathLists.clear();
                mCurrentSelectedWeathLists.addAll(currentWeatherModels);
            }
            mMitelRepository.setValue(Constants.WEATHER_DATA, new Gson().toJson(mCurrentSelectedWeathLists));
            return mCurrentSelectedWeathLists;
        });
    }

    public ArrayList<CurrentWeatherModel> getmCurrentSelectedWeathLists() {
        return mCurrentSelectedWeathLists;
    }

    public Observable<WeatherScreenScenario> getInitScenario() {
        return Observable.create(emitter -> {
            String weatherData = mMitelRepository.getStringValue(Constants.WEATHER_DATA, null);
            if (weatherData != null) {
                mCurrentSelectedWeathLists = new Gson().fromJson(weatherData, new TypeToken<List<CurrentWeatherModel>>() {
                }.getType());
            }
            if (mCurrentSelectedWeathLists == null) {
                mWeatherScreenScenario = WeatherScreenScenario.NO_LOCATION_ESISTED_SCENARIO;
            } else {
                mWeatherScreenScenario = WeatherScreenScenario.LIST_OF_LOCATIONS_SCENARIO;
            }
            emitter.onNext(mWeatherScreenScenario);
            emitter.onComplete();
        });
    }

    public void onItemsChanged(ArrayList<CurrentWeatherModel> currentWeatherModels) {
        this.mCurrentSelectedWeathLists = currentWeatherModels;
        mMitelRepository.setValue(Constants.WEATHER_DATA, new Gson().toJson(mCurrentSelectedWeathLists));
    }

}

package com.example.islam.mitelapp.wether;


import com.example.islam.mitelapp.common.EspressoIdlingResource;
import com.example.islam.mitelapp.data.models.CurrentWeatherModel;
import com.example.islam.mitelapp.data.models.LocationModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class WeatherPresenter implements WeatherContract.Presenter {
    private WeatherContract.View mView;
    private WeatherBusiness mWeatherBusiness;
    private CompositeDisposable mSubscriptions;
    private PublishSubject<String> mSearchPublishSubject;

    @Inject
    public WeatherPresenter(WeatherContract.View view, WeatherBusiness videoListBusiness) {
        this.mView = view;
        this.mWeatherBusiness = videoListBusiness;
        mSubscriptions = new CompositeDisposable();
        mView.setPresenter(this);
        mSearchPublishSubject = PublishSubject.create();
    }

    @Override
    public void onMenuExpanded() {
        mView.showSearchScenario();
        mView.hideNoLocationsSelectedScenario();
        mView.hideSelectedLocationsScenarion();
    }

    @Override
    public void getInitScenario() {
        mView.showLoading();
        EspressoIdlingResource.increment();
        mSubscriptions.add(mWeatherBusiness.getInitScenario().observeOn(AndroidSchedulers.
                mainThread()).subscribeOn(Schedulers.io()).doOnTerminate(() -> {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement(); // Set app as idle.
            }
        }).subscribe((weatherScreenScenario) -> {
            switch (weatherScreenScenario) {
                case SEARCH_SCENARIO:
                    mView.showSearchScenario();
                    mView.hideNoLocationsSelectedScenario();
                    mView.hideSelectedLocationsScenarion();
                    break;
                case LIST_OF_LOCATIONS_SCENARIO:
                    mView.showSelectedLocationsScenarion();
                    mView.selectedLOcationsScenario(mWeatherBusiness.getmCurrentSelectedWeathLists());
                    mView.hideNoLocationsSelectedScenario();
                    mView.hideSearchScenario();
                    break;
                case NO_LOCATION_ESISTED_SCENARIO:
                    mView.hideSearchScenario();
                    mView.hideSelectedLocationsScenarion();
                    mView.showNoLcationsSelectedScenario();
                    break;
            }
            mView.hideLoading();
        }, throwable -> {
            mView.hideLoading();
            mView.showError(throwable.getMessage());
        }));
    }

    @Override
    public void getLocationsListForKeyweord(String keyWord) {
        EspressoIdlingResource.increment();
        mSubscriptions.add(mSearchPublishSubject.debounce(300, TimeUnit.MILLISECONDS).skip(1)
                .distinctUntilChanged()
                .switchMapSingle(s -> mWeatherBusiness.getLocationsList(s).
                        observeOn(AndroidSchedulers.
                                mainThread()).subscribeOn(Schedulers.io())).doOnTerminate(() -> {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                })
                .subscribe((locationModels) -> {
                    mView.displaySearchLocationsList(locationModels);
                }, throwable -> {

                }));
        mSearchPublishSubject.onNext(keyWord);
    }

    @Override
    public void onLocationSeleted(LocationModel locationModel, String lastKeywrod) {
        mView.showLoading();
        EspressoIdlingResource.increment();
        mSubscriptions.add(mWeatherBusiness.getWeatherAsPerLocations(locationModel, lastKeywrod).observeOn(AndroidSchedulers.
                mainThread()).subscribeOn(Schedulers.io()).doOnTerminate(() -> {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement(); // Set app as idle.
            }
        }).subscribe((currentWeatherModels) -> {
            mView.selectedLOcationsScenario(currentWeatherModels);
            mView.showSelectedLocationsScenarion();
            mView.hideNoLocationsSelectedScenario();
            mView.hideSearchScenario();
            mView.hideLoading();
        }, throwable -> {
            mView.hideLoading();
            mView.showError(throwable.getMessage());
        }));
    }

    @Override
    public void onItemsChanged(ArrayList<CurrentWeatherModel> currentWeatherModels) {
        mWeatherBusiness.onItemsChanged(currentWeatherModels);

    }

    @Override
    public void onSwipeToRefresh() {
        mView.showLoading();
        EspressoIdlingResource.increment();
        mSubscriptions.add(mWeatherBusiness.onSwipeRefresh().observeOn(AndroidSchedulers.
                mainThread()).subscribeOn(Schedulers.io()).doOnTerminate(() -> {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement(); // Set app as idle.
            }
        }).subscribe((currentWeatherModels) -> {
            mView.selectedLOcationsScenario(currentWeatherModels);
            mView.showSelectedLocationsScenarion();
            mView.hideNoLocationsSelectedScenario();
            mView.hideSearchScenario();
            mView.hideLoading();
        }, throwable -> {
            mView.hideLoading();
            mView.showError(throwable.getMessage());
        }));
    }


    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}

package com.example.islam.mitelapp.wether;


import com.example.islam.mitelapp.app.BasePresenter;
import com.example.islam.mitelapp.app.BaseView;
import com.example.islam.mitelapp.data.models.CurrentWeatherModel;
import com.example.islam.mitelapp.data.models.LocationModel;

import java.util.ArrayList;

public interface WeatherContract {
    interface View extends BaseView<BasePresenter> {
        void displaySearchLocationsList(ArrayList<LocationModel> videoListItems);
        void showSearchScenario();
        void hideSearchScenario();
        void selectedLOcationsScenario(ArrayList<CurrentWeatherModel> currentWeatherModels);
        void showSelectedLocationsScenarion();
        void hideSelectedLocationsScenarion();
        void showNoLcationsSelectedScenario();
        void hideNoLocationsSelectedScenario();

        void hideSearchList();
    }

    interface Presenter extends BasePresenter {
        void onMenuExpanded();
        void  getInitScenario();
        void getLocationsListForKeyweord(String keyWord);
        void onLocationSeleted(LocationModel locationModel , String lastKeywrod);
        void onItemsChanged(ArrayList<CurrentWeatherModel> currentWeatherModels);

        void onSwipeToRefresh();

        void onMenuCollapsed();
    }
}

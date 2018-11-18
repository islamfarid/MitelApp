package com.example.islam.mitelapp.wether;


import com.example.islam.mitelapp.data.models.LocationModel;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class LocationsDiffCallBack extends DiffUtil.Callback {

    private final List<LocationModel> mOldLocationsList;
    private final List<LocationModel> mNewLocationsList;

    public LocationsDiffCallBack(List<LocationModel> oldEmployeeList, List<LocationModel> newEmployeeList) {
        this.mOldLocationsList = oldEmployeeList;
        this.mNewLocationsList = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return mOldLocationsList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewLocationsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldLocationsList.get(oldItemPosition).getId() == mNewLocationsList.get(
                newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final LocationModel oldLocation = mOldLocationsList.get(oldItemPosition);
        final LocationModel newLocation = mNewLocationsList.get(newItemPosition);

        return oldLocation.getId() == newLocation.getId();
    }


}
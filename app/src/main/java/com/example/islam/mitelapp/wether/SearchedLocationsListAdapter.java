package com.example.islam.mitelapp.wether;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islam.mitelapp.R;
import com.example.islam.mitelapp.common.OnListItemSelected;
import com.example.islam.mitelapp.data.models.LocationModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchedLocationsListAdapter extends RecyclerView.Adapter<SearchedLocationsListAdapter.VideosViewHolder> {
    private Context mContext;
    OnListItemSelected<LocationModel> mOnLocationSelected;
    ArrayList<LocationModel> mLocationsList;

    SearchedLocationsListAdapter(Context context, OnListItemSelected onVideoSelected, ArrayList<LocationModel> locationModels) {
        this.mContext = context;
        this.mLocationsList = locationModels;
        this.mOnLocationSelected = onVideoSelected;
    }

    @NonNull
    @Override
    public VideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_search_location_item, parent,false);
        return new VideosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VideosViewHolder holder, int position) {
        holder.locationNameText.setText(mLocationsList.get(position).getName());
        holder.locationCountry.setText(mLocationsList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return mLocationsList.size();
    }

    public class VideosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_location_name)
        AppCompatTextView locationNameText;
        @BindView(R.id.tv_location_country)
        AppCompatTextView locationCountry;

        public VideosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.rl_search_full_layout)
        void onListItemClick() {
            mOnLocationSelected.onVideoSelected(mLocationsList.get(getAdapterPosition()));
        }

    }

    public void updateLocationsListItems(List<LocationModel> locationModels) {
        final LocationsDiffCallBack diffCallback = new LocationsDiffCallBack(this.mLocationsList, locationModels);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mLocationsList.clear();
        this.mLocationsList.addAll(locationModels);
        diffResult.dispatchUpdatesTo(this);
    }

}

